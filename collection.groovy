import com.santaba.agent.groovyapi.expect.Expect;
import com.santaba.agent.groovyapi.snmp.Snmp;
import com.santaba.agent.groovyapi.http.*;
import com.santaba.agent.groovyapi.jmx.*;
import org.xbill.DNS.*;
import groovy.json.*;

//endpoint
//'https://api.tomorrow.io/v4/timelines?location=<INSERTLAT_FLOAT>,<INSERTLNG_FLOAT>&fields=treeIndex,grassIndex,weedIndex,treeAcaciaIndex,treeAshIndex,treeBeechIndex,treeBirchIndex,treeCedarIndex,treeCypressIndex,treeElderIndex,treeElmIndex,treeHemlockIndex,treeHickoryIndex,treeJuniperIndex,treeMahoganyIndex,treeMapleIndex,treeMulberryIndex,treeOakIndex,treePineIndex,treeCottonwoodIndex,treeSpruceIndex,treeSycamoreIndex,treeWalnutIndex,treeWillowIndex,grassGrassIndex,weedRagweedIndex&timesteps=current&units=metric&apikey=<INSERTAPIKEY>'

//define needed variables
def pollenapi = "api.tomorrow.io";
def pollenapikey = hostProps.get("pollenapi.key");
def pollenLocale = "##WILDVALUE##";
def lat = instanceProps.get("auto.location.lat");
def lng = instanceProps.get("auto.location.lng");
def endpointUrl = "/v4/timelines?location=${lat},${lng}&fields=treeIndex,grassIndex,weedIndex,treeAcaciaIndex,treeAshIndex,treeBeechIndex,treeBirchIndex,treeCedarIndex,treeCypressIndex,treeElderIndex,treeElmIndex,treeHemlockIndex,treeHickoryIndex,treeJuniperIndex,treeMahoganyIndex,treeMapleIndex,treeMulberryIndex,treeOakIndex,treePineIndex,treeCottonwoodIndex,treeSpruceIndex,treeSycamoreIndex,treeWalnutIndex,treeWillowIndex,grassGrassIndex,weedRagweedIndex&timesteps=current&units=metric&apikey=${pollenapikey}";

//get values for pollen indexes
def httpClient = HTTP.open(pollenapi, 80);
def response = httpClient.get(endpointUrl);
httpClient.close();
response = new JsonSlurper().parseText(httpClient.getResponseBody());
def getVals = response['data']['timelines']['intervals']['values'][0];

//process values into more easily iterable form than the non standard json received
def rawVals = getVals.toString();
def procStr_0 = rawVals.replaceAll("[\\[\\](){}]","");
def procStr_1 = procStr_0.replaceAll(":", "=");
def vals = procStr_1.split(", ");

//print values for datapoints
for(i=0; i < vals.size(); i++){
    println(vals[i]);
}

return 0
