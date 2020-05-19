(ns json-path
  [:require [json-path.parser :as parser]
   [json-path.match :as m]
   [json-path.walker :as walker]])

(defn query [path object & {:keys [keywords?]
                            :or {keywords? true}}]
  (binding [walker/*keywords?* keywords?]
    (walker/walk (parser/parse-path path) {:root (m/root object)})))

(defn at-path [path object & opts]
  (walker/map# :value (apply query path object opts)))
