(ns foreclojure.messages
  (:use [clojure.java.io :only [resource input-stream]]))

(defn load-props [file-name]
  (let [^java.net.URL url (resource file-name)]
    (with-open [^java.io.BufferedInputStream stream (input-stream url)]
      (into {} (doto (java.util.Properties.)
                 (.load stream))))))
 
(def err-msg-map  (load-props "error-messages.properties"))             
             
(defn err-msg [key & args]
      (apply format (cons (get err-msg-map key) args)))