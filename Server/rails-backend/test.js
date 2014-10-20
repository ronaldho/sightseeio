 var auth = { 
    consumerKey: "byebZmVlhZe12Wxk3Cw9ww", 
    consumerSecret: "XGft9CBUdoeNHetks3RNE_akijE",
    accessToken: "E_hTMDH1N1pway_ZHzhk9yx5synssvdZ",
    accessTokenSecret: "zbkyOqQ8KFslDGw9dOIF8IB99xs",
  };

  var terms = 'food';
  var near = 'San+Francisco';

  var accessor = {
    consumerSecret: auth.consumerSecret,
    tokenSecret: auth.accessTokenSecret
  };

  var parameters = [];
  parameters.push(['term', terms]);
  parameters.push(['location', near]);
  parameters.push(['oauth_consumer_key', auth.consumerKey]);
  parameters.push(['oauth_consumer_secret', auth.consumerSecret]);
  parameters.push(['oauth_token', auth.accessToken]);

  var message = { 
    'action': 'http://api.yelp.com/v2/search',
    'method': 'GET',
    'parameters': parameters 
  };

  OAuth.setTimestampAndNonce(message);  

  OAuth.SignatureMethod.sign(message, accessor);

  var parameterMap = OAuth.getParameterMap(message.parameters);
  parameterMap.oauth_signature = OAuth.percentEncode(parameterMap.oauth_signature)

  var url = OAuth.addToURL(message.action,parameterMap);
  var response = UrlFetchApp.fetch(url).getContentText();
  var responseObject = Utilities.jsonParse(response);
	console.log(responseObject);
  //have my JSON object, do whatever we want here, like add to spreadsheets
