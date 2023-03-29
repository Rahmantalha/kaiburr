@Configuration
public class MongoConfig {
 
    @Bean
    public MongoDbFactory mongoDbFactory() {
        return new SimpleMongoClientDbFactory("mongodb://localhost:27017/serversdb");
    }
 
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }
}

public class Server {
    private String name;
    private String id;
    private String language;
    private String framework;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }
}

@RestController
@RequestMapping("/servers")
public class ServerController {
 
    @Autowired
    private MongoTemplate mongoTemplate;
 
    @GetMapping("")
    public List<Server> getServers(@RequestParam(name = "id", required = false) String id) {
        if (id == null) {
            return mongoTemplate.findAll(Server.class);
        } else {
            return Collections.singletonList(mongoTemplate.findById(id, Server.class));
        }
    }
 
    @PutMapping("")
    public void addServer(@RequestBody Server server) {
        mongoTemplate.save(server);
    }
 
    @DeleteMapping("/{id}")
    public void deleteServer(@PathVariable("id") String id) {
        mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), Server.class);
    }
 
    @GetMapping("/find")
    public List<Server> findServersByName(@RequestParam("name") String name) {
        return mongoTemplate.find(Query.query(Criteria.where("name").regex(name)), Server.class);
    }
}

@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}

