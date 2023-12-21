(ns day4.part1
  (:require
   [clojure.java.io :as io]
   [clojure.string :as string]
   [clojure.set :refer [intersection]]))

(defn numbers [line]
  (-> line
      (string/split #": ")
      (get 1)
      (string/split #" \| ")
      ((partial map #(string/split %1 #"\s+")))
      ((partial map set))))

(defn points [winning]
  (reduce
   (fn [acc _]
     (if (= acc 0) 1 (* 2 acc)))
   0
   winning))

(defn solve [input]
  (->> (map numbers input)
       (map #(apply intersection %))
       (map points)
       (reduce +)))

(with-open [rdr (io/reader "day4/sample1.txt")]
  (solve (line-seq rdr)))

(with-open [rdr (io/reader "day4/input.txt")]
  (solve (line-seq rdr)))

