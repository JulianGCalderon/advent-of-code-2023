(ns day1.part1
  (:require [clojure.java.io :as io]))

(defn numbers [line]
  (re-seq #"\d" line))

(defn calibration-values [line]
  (let [nums (numbers line)
        n1 (first nums)
        n2 (last nums)]

    (Integer. (str n1 n2))))

(with-open [rdr (io/reader "day1/input.txt")]
  (reduce + (map calibration-values (line-seq rdr))))

