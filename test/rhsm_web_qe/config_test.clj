(ns rhsm-web-qe.config-test
  (:require [clojure.test :refer :all]
            [rhsm-web-qe.config :refer :all]
            [environ.core :refer [env]]
            [clojure.string :as s]
            ))

(deftest cockpit-config-test
  (testing "COCKPIT_HOST is set?"
    (is (not (s/blank? (-> :sm-cockpit-host env)))))
  (testing "COCKPIT_PORT is set?"
    (is (not (s/blank? (-> :sm-cockpit-port env)))))
  )

(deftest ssh-key-test
  (testing "ssh key pasphrase is set to get into cockpit"
    (is (re-find #"[a-z]+" (-> :sm-sshkey-passphrase env))))
  (testing "ssh key private key path is set to get into cockpit"
    (is (re-find #"[a-z]+" (-> :sm-sshkey-private env)))))
