package com.bigdatacompany.twitter;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.bson.Document;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KafkaProducerApp {
    public static void main(String[] args) throws TwitterException {
        Properties config =new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,new StringSerializer().getClass().getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,new StringSerializer().getClass().getName());
        KafkaProducer<String, String> Producer = new KafkaProducer<String, String>(config);

        ConfigurationBuilder cb = ConfigrationTwitter.getConfig();

        Twitter twitter =new TwitterFactory(cb.build()).getInstance();

        ArrayList<Status> tweets = ConfigrationTwitter.getAdvancedSearch(twitter);

        for (int i = 0; i < tweets.size(); i ++){
            Status st = (Status) tweets.get(i);
            JSONObject jsonObject= new JSONObject();
            jsonObject.put("screen_name",st.getUser().getScreenName());
            jsonObject.put("twit",st.getText());
            jsonObject.put("create_date",st.getCreatedAt().getTime());
            jsonObject.put("followers_count",st.getUser().getFollowersCount());
            jsonObject.put("friends_count",st.getUser().getFriendsCount());
            jsonObject.put("description",st.getUser().getDescription());
            jsonObject.put("favorite_count",st.getFavoriteCount());
            jsonObject.put("retweet_count",st.getRetweetCount());
            String email = st.getUser().getEmail();
            if (email != null)
                jsonObject.put("email",email);
            System.out.println(jsonObject.toString());
            ProducerRecord<String, String> rec = new ProducerRecord<String, String>("twitter-search", jsonObject.toString());
            Producer.send(rec);


        }
    }

}
