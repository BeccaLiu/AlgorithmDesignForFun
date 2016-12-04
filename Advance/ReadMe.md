1, draw the solution tree
2, find duplicated tree in solution tree
3, delete or pruning, ->greedy

1, draw the [solution tree], using small test case and enum all the possiblity
2, came up with a recursion
3, find the duplicate part of the solution, using array to store that info in case second time check up [Memorized Search]
4, convert recursion to dp, from bottom up(recursion) to to down solution[dynamic programming]
5, using greedy to improve the complexity of time or space base on dp[Greedy]

binary index tree
follow up from sumRange1D
0 1 2 3 4 5 6 7
1 2 3 4 5 6 7 8
1   3   5   7
  3       11
      10
              36

update(2,0)-> a[2]=0 -> its parent a[3] -> a[3] parent a[7]
sumrange=(1,5) -> a[5]+a[3]-a[1]

key is to find parent:
and using a table
i&(-i) to find how many steps it goona take to find parent