(ns core
  (:require [clj-http.client :as http]))

(defn get-bearer-token [secrets url]
  (let [response (http/post url {:basic-auth [(:apikey secrets) (:apisecret secrets)]
                                 :form-params {:grant_type "client_credentials"}})]
    (:body response)))
