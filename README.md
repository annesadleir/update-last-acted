# update-last-acted

## purpose
It's a hassle making and uploading a new lastActed.txt for the atom watcher when devving.  This makes it a bit faster

### input
Json with mandatory value for  "bucket" and optional value for "time".  
```json
{
"bucket": "last-acted-bucket",
"time": "2020-10-03T15:52:00Z"
}
```
If the time value is omitted, the text value will be set to Epoch.  
If the time value is "now", the text value will be set to now. 
Otherwise the time value must be a valid ISO date time.

