# Redis键值格式

## tweets
type: set  
关注的推主的id的set  

## tweet_qq_{tweetId}_{groupId}
type: map  
关注tweetId的群groupId的信息  

## bili_cookie_{biliId}
type: map  
bilicookie  

## tweet_bili_{tweetId}_{biliId}
type: map