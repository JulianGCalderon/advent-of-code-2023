(ns day2.part1
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defrecord Game [id rounds])

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

(defn possible-round [round]
  (and (<= (get round "red" 0) 12)
       (<= (get round "green" 0) 13)
       (<= (get round "blue" 0) 14)))

(defn possible [game]
  (every? possible-round (:rounds game)))

(defn part1 [rdr]
  (->> (line-seq rdr)
       (map parse-game)
       (filter possible)
       (map :id)
       (reduce +)))

(with-open [rdr (io/reader "day2/input.txt")]
  (part1 rdr))

