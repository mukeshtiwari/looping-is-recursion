(ns looping-is-recursion)

(defn power [base exp]
  (if (= base 0) 0
      (let [pow-helper (fn [acc n]
                         (if (= n 0) acc
                             (recur (* acc base) (- n 1))))]
        (pow-helper 1 exp))))

(power 2 2)  ;=> 4
(power 5 3)  ;=> 125
(power 7 0)  ;=> 1
(power 0 10) ;=> 0


(defn last-element [a-seq]
  (cond
    (empty? a-seq) nil
    (empty? (rest a-seq)) (first a-seq)
    :else (recur (rest a-seq))))


(last-element [])      ;=> nil
(last-element [1 2 3]) ;=> 3
(last-element [2 5])   ;=> 5

(defn seq= [seq1 seq2]
  (cond
    (and (empty? seq1) (empty? seq2)) true
    (or
      (and (empty? seq1) (not (empty? seq2)))
      (and (not (empty? seq1)) (empty? seq2))
      (not (= (first seq1) (first seq2)))) false
    :else (recur (rest seq1) (rest seq2))))

(seq= [1 2 4] '(1 2 4))  ;=> true
(seq= [1 2 3] [1 2 3 4]) ;=> false
(seq= [1 3 5] [])        ;=> false

(defn find-first-index [pred a-seq]
  (loop [cnt 0
         se a-seq]
    (cond
      (empty? se) nil
      (pred (first se)) cnt
      :else (recur (+ cnt 1) (rest se)))))


(find-first-index zero? [1 1 1 3 7 2 0])                    ;=> 3
(find-first-index zero? [1 1 3 7 2])                          ;=> nil
(find-first-index (fn [n] (= n 6)) [:cat :dog :six :blorg 6]) ;=> 4
(find-first-index nil? [])                                    ;=> nil

(defn avg [a-seq]
  (if (empty? a-seq) 0
      (loop [cnt 0
             cursum 0
             se a-seq]
        (cond
          (empty? se) (/ cursum cnt)
          :else (recur (+ cnt 1) (+ cursum (first se)) (rest se))))))

(avg [1 2 3])   ;=> 2
(avg [0 0 0 4]) ;=> 1
(avg [1 0 0 1]) ;=> 1/2 ;; or 0.5

(defn parity [a-seq]
  (let [fren (frequencies a-seq)]
    (set (for [[k v] fren
               :when (odd? v)] k))))

(parity [:a :b :c])           ;=> #{:a :b :c}
(parity [:a :b :c :a])        ;=> #{:b :c}
(parity [1 1 2 1 2 3 1 2 3 4]) ;=> #{2 4}

(defn fast-fibo [n]
  (loop [cnt 0
         fv 0
         sv 1]
    (if (= cnt n) fv
        (recur (+ cnt 1) sv (+ fv sv)))))

(fast-fibo 0) ;=> 0
(fast-fibo 1) ;=> 1
(fast-fibo 2) ;=> 1
(fast-fibo 3) ;=> 2
(fast-fibo 4) ;=> 3
(fast-fibo 5) ;=> 5
(fast-fibo 6) ;=> 8

(defn cut-at-repetition [a-seq]
  (loop [xs a-seq
         ys []
         acc #{}]
    (let [el (first xs)]
      (if (or (contains? acc el)
              (empty? xs)) ys
        (recur (rest xs)
               (conj ys el)
               (conj acc el))))))

; (sorted-set a-seq)
(cut-at-repetition [1 1 1 1 1])
;=> [1] doesn't have to be a vector, a sequence is fine too
(cut-at-repetition [:cat :dog :house :milk 1 :cat :dog])
;=> [:cat :dog :house :milk 1]
(cut-at-repetition [0 1 2 3 4 5])
;=> [0 1 2 3 4 5]
