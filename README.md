![image](https://user-images.githubusercontent.com/42879032/232492768-d68aabd8-7001-419b-8cc8-c1565a8fd751.png)

Written for LogicMonitor's April 2023 community Contest. Data is drawn from api.tomorrow.io. You can learn more about their free datasets here: https://docs.tomorrow.io/reference/weather-data-layers 

Rate limits do apply.

Installation instructions:

1) Become a free member and generate API keys at the following two sites:
    For GeoLocation: https://opencagedata.com/
    For Pollen data: https://tomorrow.io

2) Give an existing resource/resource group these properties:

    1) geoapi.key = api token from opencage.com
    2) pollenapi.key = api token from tomorrow.io
    3) location = This can be 1 city or an array of cities in the following format:  
    City,ST;City,ST;City,ST; etc.
    ** Note each City,ST string must be separated by a semi-colon (;). Example: 
    Austin,TX;LasVegas,NV;...etc.
    4) Append system.categories with the value api.tomorrow.io
    

3) The module is set to collect data hourly for each of 7 locations in the United States. Bear in mind that opencage.com and tomorrow.io both have rate limits in place, so you shouldn't try to collect data for more cities than rate limiting will allow or you will see No Data painted in the portal and errors may be thrown.

5) Thresholds have been set for overall indexes - treeIndex, grassIndex, weedIndex.

6) Poll values indicate current value of datapoint.

7) More details can be found in the code comments.

Ratings Explained:

0: None

1: Very low

2: Low

3: Medium

4: High

 5: Very High
