type RandomizedSet struct {
    arr  []int
    ht  map[int]int
}


/** Initialize your data structure here. */
func Constructor() RandomizedSet {
    return RandomizedSet{
        ht: map[int]int{},
    }
}


/** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
func (this *RandomizedSet) Insert(val int) bool {
    if _, found := this.ht[val]; found {
        return false
    }
    this.ht[val] = len(this.arr)
    this.arr = append(this.arr, val);

    return true
}


/** Removes a value from the set. Returns true if the set contained the specified element. */
func (this *RandomizedSet) Remove(val int) bool {
    rmIdx, found := this.ht[val]
    if !found {
        return false
    }
    lastIdx := len(this.arr) - 1
    rm, last := this.arr[rmIdx], this.arr[lastIdx]
    
    this.arr[rmIdx] = this.arr[lastIdx]
    this.ht[last] = rmIdx
    delete(this.ht, rm)
    this.arr = this.arr[: lastIdx]
    return true
}


/** Get a random element from the set. */
func (this *RandomizedSet) GetRandom() int {
    return this.arr[rand.Intn(len(this.arr))]
}


/**
 * Your RandomizedSet object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Insert(val);
 * param_2 := obj.Remove(val);
 * param_3 := obj.GetRandom();
 */