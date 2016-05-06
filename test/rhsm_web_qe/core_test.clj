(ns rhsm-web-qe.core-test
  (:require [clojure.test :refer :all]
            [rhsm-web-qe.core :refer :all]
            [environ.core :refer [env]]
            [clj-ssh.ssh :as ssh]
            )
  (:use clj-webdriver.taxi
        clj-webdriver.window
        [clojure.java.shell :only [sh]]
        )
  )

(comment (= "" "cannot make this run")
         (let [agent (ssh/ssh-agent {:use-system-ssh-agent true})]
           (ssh/add-identity agent {:private-key-path (-> :sm-sshkey-private env)
                                    :public-key-path  (-> :sm-sshkey-public env)
                                    :passphrase (-> :sm-sshkey-passphrase env .getBytes)})
           (let [session (ssh/session agent "localhost" {:strict-host-key-checking :no})]
             (ssh/with-connection session
               (ssh/ssh session {:cmd "ls" :username "jstavel"})
               )))
         )


(set-driver! {:browser :firefox})
(window-maximize)

(defn login [user password]
  (to (format "https://%s:%s" (env :sm-cockpit-host) (env :sm-cockpit-port)))
  (wait-until #(exists? "#login-user-input"))
  (input-text "#login-user-input" user )
  (input-text "#login-password-input" password)
  (click "#login-button")
  (wait-until #(exists? "#content-user-name")))

(deftest subscription-dialog
  (login (env :sm-cockpit-login-user) (env :sm-cockpit-login-password))
  (click {:xpath "//a[@data-target='#tools-panel']"})
  (wait-until #(displayed? {:xpath "//a[@href='/subscriptions']"}))
  (click {:xpath "//a[@href='/subscriptions']"})
  (close))
