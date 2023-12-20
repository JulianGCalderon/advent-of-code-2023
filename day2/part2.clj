(ns day2.part2
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defrecord Game [id rounds])
(def colors ["red" "green" "blue"])

(defn parse-id [line]
  (Integer. (last (string/split line #" "))))

(defn parse-round [line]
  (into {} (for [reveal (string/split line #", ")]
             (-> (string/split reveal #" ")
                 (update-in [0] #(Integer/parseInt %1))
                 (reverse)
                 (vec)))))

(defn parse-rounds [line]
  (map parse-round (string/split line #"; ")))

(defn parse-game [line]
  (let [[id rounds] (string/split line #": " 2)]
    (->Game
     (parse-id id)
     (parse-rounds rounds))))

(defn minimum-set [game]
  (reduce
   (partial merge-with max)
   (zipmap colors (repeat 0))
   (:rounds game)))

(defn power [set]
  (reduce * (vals set)))

(defn part1 [rdr]
  (->> (line-seq rdr)
       (map parse-game)
       (map minimum-set)
       (map power)
       (reduce +)))

(with-open [rdr (io/reader "day2/input.txt")]
  (part1 rdr))

