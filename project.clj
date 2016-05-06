(defproject rhsm-web-qe "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [environ "1.0.2"]
                 [clj-webdriver "0.7.2"]
                 [clj-ssh "0.5.14"]
                 [mount "0.1.10"]
                 [org.seleniumhq.selenium/selenium-server "2.52.0"]
                 ]
  :plugins [[quickie "0.4.1"]
            ]
  )
