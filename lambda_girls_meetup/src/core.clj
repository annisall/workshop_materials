(ns core
  (:require [parser :as p]
            [twttr.auth :as tw-auth]
            [twttr.api :as tw-api]))

(defn read-tweets-from-stream [api-key api-secret-key oauth-token oauth-token-secret]
  (let [creds (tw-auth/map->UserCredentials {:consumer-key api-key :consumer-secret api-secret-key :user-token oauth-token :user-token-secret oauth-token-secret})]
    (doseq [tweet (take 10 (tw-api/statuses-filter creds :params {:track "#womenintech"}))]
      (prn (p/get-tweet-map tweet)))))
