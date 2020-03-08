(defproject data-pipe-line-demo "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [clj-http "3.10.0"]
                 [proto-repl "0.3.1"]
                 [cheshire "5.10.0"]
                 [buddy "2.0.0"]
                 [crypto-random "1.2.0"]
                 [twttr "3.2.1"]]
  :source-paths ["src"]
  :repl-options {:port 8081})
