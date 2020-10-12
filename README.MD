#HashMap Implementation 

<h5> This hashmap implemention implement the Map interface in the same package 
and support functions include APIs put, get, size, containsKey, and remove. It also implements
a iterator that can go though all item in the list </h5>

<p>The hashmap has the following methods
<li>HashMap() 
<li>HashMap(int, float)
<li>iterator
<li>size
<li>isEmpty
<li>hash
<li>containsKey
<li>get
<li>put
<li>resize
<li>remove</li> </p>

<p>
The hashmap is implemented with separate chaining method.
<p> When put an new key value pair, after calculating hashcode, it finds the array index corresponding to
the hashcode and chain to the head of the node in this location.
And the internal array is resized when the load factor is reached.</p>

<p>
Get/remove/containsKey method basically following the same method to find the key
and just do different operations after finding the key</p>

<p>
The iterator implementation uses a count to record the index in the 
internal array, and check the node one by one until, and then go to 
the next index in the internal array. </p>

<p>
The test is using Junit, put, get and iterator are tested together,
with 32 elements used in this test to check resize as well,
other method test are written separately. </p> 

