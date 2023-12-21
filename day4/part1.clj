(ns day4.part1
  (:require
   [clojure.java.io :as io]
   [clojure.string :as string]
   [clojure.set :refer [intersection]]))

(defn winners [line]
  (->
   (->
    line
    (string/split #":")
    (get 1)
    (string/split #"\|"))
   (->>
    (map #(string/trim %1))
    (map #(string/split %1 #"\s+"))
    (map set)
    (apply intersection)
    (count))))

(defn points [winners]
  (reduce
   (fn [acc _]
     (if (= acc 0) 1 (* 2 acc)))
   0
   (range winners)))

(defn solve [input]
  (->> (map winners input)
       (map points)
       (reduce +)))

(with-open [rdr (io/reader "day4/sample1.txt")]
  (solve (line-seq rdr)))

(with-open [rdr (io/reader "day4/input.txt")]
  (solve (line-seq rdr)))

