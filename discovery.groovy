import com.santaba.agent.groovyapi.expect.Expect;
import com.santaba.agent.groovyapi.snmp.Snmp;
import com.santaba.agent.groovyapi.http.*;
import com.santaba.agent.groovyapi.jmx.*;
import org.xbill.DNS.*;
import groovy.json.JsonSlurper;

//define variables
def apikey = hostProps.get("geoapi.key");
def locapi = "api.opencagedata.com";

//make location array
//the value of "location" on device/device is a string of city names separated by semi-colons, i.e. Austin,TX;SantaBarbara,CA;NewYork,NY etc.
//or it can simply be one location set on a device i.e. Austin,TX
//note: using anything other than a semi-colon to separate the city,state names will break this.

def locations = hostProps.get("location");
//println(locations)
//splits the string at the semi-colon
def locSplit = locations.split(";");
//assigns each split value to locationList
def locationList = [];
for(i=0; i < locSplit.size(); i++){
    locationList[i] = locSplit[i].replaceAll(" ","");
}
//creates instances of each city value from the locationList list
for(i =0; i < locationList.size(); i++){
    def endpoint = "https://api.opencagedata.com/geocode/v1/json?q=${locationList[i]}&key=${apikey}&language=en&pretty=1";
    def port = 80;
    def httpClient = HTTP.open(locapi, port);
    def response = httpClient.get(endpoint);
    def statusCode = httpClient.getStatusCode();
    httpClient.close();
    if(statusCode == 200){
        response = new JsonSlurper().parseText(httpClient.getResponseBody());
        def citystate = locationList[i];
        def latitude = response['results'][0]['geometry']['lat'];
        def longitude = response['results'][0]['geometry']['lng'];
        def id = citystate+"##";
        def desc = citystate+"####";
        def lat = "auto.location.lat=${latitude}";
        def lng = "auto.location.lng=${longitude}";
        println(id+id+desc+lat+"&"+lng);
    } else {
        println("Your http get request was not successful. StatusCode = ${statusCode}");

    }
}


