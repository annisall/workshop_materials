(ns parser
  (:require [clojure.set :as set]))

(defn get-user-info [user-map]
  (-> (select-keys user-map [:name :screen_name :id :location])
      (set/rename-keys {:id :user_id})))

(defn get-full-tweet-text [tweet]
  (if (:truncated tweet) (get-in tweet [:extended_tweet :full_text]) (:text tweet)))

(defn get-tweet-map [tweet-contents]
  (-> (select-keys tweet-contents [:lang :id :retweet_count :favorite_count :created_at])
      (merge (get-user-info (:user tweet-contents)))
      (merge (get-full-tweet-text tweet-contents))))
