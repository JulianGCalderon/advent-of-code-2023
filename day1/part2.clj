(ns day1.part2
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(def translation {"one" "1"
                  "two" "2"
                  "three" "3"
                  "four" "4"
                  "five" "5"
                  "six" "6"
                  "seven" "7"
                  "eight" "8"
                  "nine" "9"})

(defn translate [digit] (or (translation digit) digit))

(def digits-pattern
  (re-pattern (format "(?=(%s|%s))"
                      #"\d"
                      (string/join "|" (keys translation)))))

(defn numbers [line]
  (map
   (fn [[_, digit]] (translate digit))
   (re-seq digits-pattern line)))

(defn calibration-values [line]
  (let [nums (numbers line)
        n1 (first nums)
        n2 (last nums)]

    (Integer. (str n1 n2))))

(with-open [rdr (io/reader "day1/input.txt")]
  (reduce + (map calibration-values (line-seq rdr))))

