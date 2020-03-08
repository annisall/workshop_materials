(ns core
  (:require [parser :as p]
            [twttr.auth :as tw-auth]
            [twttr.api :as tw-api]))

(defn get-bearer-token [secrets url]
  (let [response (http/post url {:basic-auth [(:apikey secrets) (:apisecret secrets)]
                                 :form-params {:grant_type "client_credentials"}})]
    response))

(defn get-tweets-by-hashtag [bearer-token hashtag base-url]
  (let [response (http/get base-url {:query-params {:q hashtag
                                                    :count 100}
                                     :oauth-token bearer-token})]
    (json/parse-string (:body response) true)))

(defn get-tweets-from-stream [authentication-headers hashtag base-url]
  (let [stream (http/get base-url {:query-params {:track hashtag}
                                   :headers authentication-headers
                                   :as :stream})]
    (print (json/parse-string (:body stream) true))))

(defn read-tweets-from-stream [api-key api-secret-key oauth-token oauth-token-secret]
  (let [creds (tw-auth/map->UserCredentials {:consumer-key api-key :consumer-secret api-secret-key :user-token oauth-token :user-token-secret oauth-token-secret})]
    (doseq [tweet (tw-api (api/statuses-filter creds :params {:track "#womenintech"}))]
      (prn (p/get-tweet-map tweet)))))
