namespace java ezbake.ins.thrift.gen

include "ezbakeBaseTypes.thrift"
include "ezbakeBaseService.thrift"
include "EzSecurityRegistration.thrift"

const string SERVICE_NAME = "ins"

struct AppService {
	1: required string applicationName,
	2: required string serviceName
}

struct BroadcastTopic {
	1: optional string name,
	2: optional string description,
	3: optional string location, //deprecated
	4: optional string thriftDefinition

}

struct FeedPipeline {
	1: optional string feedName,
	2: optional string description,
	3: optional set<BroadcastTopic> broadcastTopics,
    4: optional string exportingSystem,
    5: optional string type,
    6: optional string maxClassification,
    7: optional string networkInitiated,
    8: optional list<string> physicalServers,
    9: optional string dateAdded,
    10: optional string dataType
}

struct JobRegistration {
	1: optional string jobName,
	2: optional string feedName,
	3: optional string uriPrefix
}

struct ListenerPipeline {
	1: optional string feedName,
	2: optional string description,
	3: optional set<string> listeningTopics,
	4: optional set<BroadcastTopic> broadcastTopics
}

struct WebApplicationLink {
    1: optional string appName,
    2: optional string webUrl,
    3: optional bool includePrefix,
    4: optional string requiredGroupName
}

struct WebApplication {
	1: optional map<string, WebApplicationLink> urnMap,
	2: optional bool isChloeEnabled,
	3: optional string chloeWebUrl,
	4: optional string externalUri,
	5: optional string requiredGroupName
}

struct Application {
        1: optional string id,
	2: optional string appName,
	3: optional string poc,
	4: optional set<string> allowedUsers,
	5: optional map<string, string> categories,
	6: optional set<FeedPipeline> feedPipelines,
	7: optional set<ListenerPipeline> listenerPipelines,
	8: optional WebApplication webApp,
	9: optional set<string> authorizations,
	10: optional map<string, set<string>> authorizationBuilder,
	11: optional map<string, string> intentServiceMap,
	12: optional set<JobRegistration> jobRegistrations,
	13: optional string appIconSrc,
	14: optional string sponsoringOrganization,
	15: optional set<string> communityAuthorizations
}

struct ApplicationSummary {
    1: optional string id
	2: optional string appName
	3: optional string poc
	4: optional string appIconSrc
	5: optional string sponsoringOrganization
	6: optional string externalUri
}

enum FeedType {
   APP,
   SYSTEM,
   ALL
}

exception ApplicationNotFoundException {
    1: required string message;
}

service InternalNameService extends ezbakeBaseService.EzBakeBaseService {
    /**
    * Saves a new or updates an existing application.  Only the INS website or deployer may call this function
    **/
    bool saveApplication(1: Application application, 2: ezbakeBaseTypes.EzSecurityToken token) throws (1:ezbakeBaseTypes.EzSecurityTokenException ex1),
    /**
    * Deletes an existing application.  Only the INS website or deployer may call this function
    **/
    bool deleteApplication(1: string appId, 2: ezbakeBaseTypes.EzSecurityToken token) throws (1:ezbakeBaseTypes.EzSecurityTokenException ex1),
    /**
    * Find duplicate application names. Only the INS website or deployer may call this function
    **/
    set<Application> getDuplicateAppNames(1: string appName, 2: ezbakeBaseTypes.EzSecurityToken token) throws (1:ezbakeBaseTypes.EzSecurityTokenException ex1),
    /**
    * Gets an existing application with the given Id.  Only the INS website or deployer may call this function
    **/
    Application getAppById(1: string appId, 2: ezbakeBaseTypes.EzSecurityToken token) throws (1:ApplicationNotFoundException ex1, 2:ezbakeBaseTypes.EzSecurityTokenException ex2),
    /**
    * Gets all the applications that the given user is an INS admin of.  Only the INS website or deployer may call this function
    **/
    set<Application> getMyApps(1: ezbakeBaseTypes.EzSecurityToken token) throws (1:ezbakeBaseTypes.EzSecurityTokenException ex1),
    /**
    * Gets basic information for the given app name.  Open for all users
    **/
    ApplicationSummary getAppByName(1: string appName, 2: ezbakeBaseTypes.EzSecurityToken token) throws (1:ApplicationNotFoundException ex1, 2:ezbakeBaseTypes.EzSecurityTokenException ex2)

    /*******    Category Functions   **********/
    /**
    * Gets all Feed categories.  Open for all users
    **/
    set<string> getCategories(),
    /**
    * Adds a new feed category.  Only the INS website or deployer may call this function
    **/
    bool addCategory(1: string category, 2: ezbakeBaseTypes.EzSecurityToken token) throws (1:ezbakeBaseTypes.EzSecurityTokenException ex1),
    /**
    * Removes an existing feed category.  Only the INS website or deployer may call this function
    **/
    bool removeCategory(1: string category, 2: ezbakeBaseTypes.EzSecurityToken token) throws (1:ezbakeBaseTypes.EzSecurityTokenException ex1),

    /*******    Topic Functions     **********/
    /**
    * Gets all System Topics (topics that anyone can broadcast to).  Open for all users
    **/
    set<string> getSystemTopics(),
    /**
    * Adds a System Topic.  Only the INS website or deployer may call this function
    **/
    bool addSystemTopic(1: string systemTopic, 2: ezbakeBaseTypes.EzSecurityToken token) throws (1:ezbakeBaseTypes.EzSecurityTokenException ex1),
    /**
    * Removes an existing System Topic.  Only the INS website or deployer may call this function
    **/
    bool removeSystemTopic(1: string systemTopic, 2: ezbakeBaseTypes.EzSecurityToken token) throws (1:ezbakeBaseTypes.EzSecurityTokenException ex1),

    /*********  Pipeline Functions  **********/
    /**
    * Gets all the registered feeds.  Open for all users
    **/
	set<FeedPipeline> getPipelineFeeds(),
    /**
    * Gets all topics that a given application is registered to broadcast to for a given feed.  Open for all users
    **/
    set<string> getTopicsForFeed(1: string appId, 2: string feedName) throws (1:ApplicationNotFoundException ex1),
    /**
    * Gets all topics that a given application is approved to broadcast to for a given feed.  Only the INS website or deployer may call this function
    **/
    set<string> getApprovedTopicsForFeed(1: string appId, 2: string feedName, 3: ezbakeBaseTypes.EzSecurityToken token) throws (1:ApplicationNotFoundException ex1, 2:EzSecurityRegistration.RegistrationException ex2, 3:EzSecurityRegistration.SecurityIDNotFoundException  ex3, 4:EzSecurityRegistration.PermissionDeniedException  ex4, 5:ezbakeBaseTypes.EzSecurityTokenException ex5),
    /**
    * Gets all topics that a given application is approved to listen to for a given feed.  Only the INS website or deployer may call this function
    **/
    set<string> getListeningTopicsForFeed(1: string appId, 2: string feedName, 3: ezbakeBaseTypes.EzSecurityToken token) throws (1:ApplicationNotFoundException ex1, 2:EzSecurityRegistration.RegistrationException ex2, 3:EzSecurityRegistration.SecurityIDNotFoundException  ex3, 4:EzSecurityRegistration.PermissionDeniedException  ex4, 5:ezbakeBaseTypes.EzSecurityTokenException ex5),
    /**
    * Gets all the currently registered broadcast topics based on the type.  Open for all users
    **/
    set<string> allBroadcastTopicNames(1: FeedType type),

    /********   Web App Functions   *********/
    /**
    * Gets the prefix for the URI that should be used for the given application and feed name combination.  Open for all users
    **/
    string getURIPrefix(1: string appId, 2: string categoryKey) throws (1:ApplicationNotFoundException ex1),
    /**
    * Gets all the URI prefixes current registered in the system.  Open for all users
    **/
    set<string> getURIPrefixes(),
    /**
    * Gets all applications that capable of opening or visualizing the given uri (or uri prefix).  Open for all users
    **/
    set<WebApplicationLink> getWebAppsForUri(1: string uri),
    /**
    * Gets all Chloe-enabled applications (applications that support the Chloe API for sharing data).  Open for all users
    **/
    set<WebApplicationLink> getChloeWebApps(),

    /********   Other registration items    *****/
    /**
    * Gets all application services that can answer the given intent.  Open for all users
    **/
    set<AppService> appsThatSupportIntent(1: string intentName),
    /**
    * Gets the approved batch jobs for the given application.  Only the INS website or deployer may call this function
    **/
    set<JobRegistration> getJobRegistrations(1: string appId, 2: ezbakeBaseTypes.EzSecurityToken token) throws (1:ApplicationNotFoundException ex1, 2:ezbakeBaseTypes.EzSecurityTokenException ex2),


    /********   Import/Export Functions *********/
    /**
    * Exports all details of an existing application as a JSON string.  Only the INS website or deployer may call this function
    **/
    string exportApplication(1: string appId, 2: ezbakeBaseTypes.EzSecurityToken token) throws (1:ApplicationNotFoundException ex1, 2:ezbakeBaseTypes.EzSecurityTokenException ex2),
    /**
    * Imports a JSON representation of an application.  Only the INS website or deployer may call this function
    **/
    Application importApplication(1: string exportedApplication, 2: ezbakeBaseTypes.EzSecurityToken token) throws (1:ezbakeBaseTypes.EzSecurityTokenException ex1)
}
