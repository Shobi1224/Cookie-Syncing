Cookie syncing: with Elastic Search

1.User visists Mro website
2.mroCookie is created and set
3.the embedded scriptin website calls cksync with mroCookieId
4.cksync looks for mroSyncIdId in table if found updates or create
5.cksync sends 302 redirect to adx and fluct with mroSyncId
6.adx and fluct set their cookie in the header and redirect it back to  with mroSyncId
7.cksync matches and Stores the adx and fluctId with the mroSyncId