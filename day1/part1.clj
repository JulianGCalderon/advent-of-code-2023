(ns day1.part1
  (:require [clojure.java.io :as io]))

(defn numbers [line]
  (re-seq #"\d" line))

(defn calibration-values [line]
  (let [nums (numbers line)
        n1 (first nums)
        n2 (last nums)]

    (Integer. (str n1 n2))))

(defn solve [rdr]
  (->> (line-seq rdr)
       (map calibration-values)
       (reduce +)))

(with-open [rdr (io/reader "day1/input.txt")]
  (solve rdr))

