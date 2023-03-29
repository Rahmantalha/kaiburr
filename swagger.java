swagger: "2.0"
info:
  version: "1.0.0"
  title: "Server API"
paths:
  /servers:
    get:
      description: "Returns all servers if no parameters are passed. When server id is passed as a parameter - return a single server or 404 if there’s no such a server."
      produces:
        - "application/json"
      parameters:
        - name: "id"
          in: "query"
          description: "Server ID"
          required: false
          type: "string"
      responses:
        200:
          description: "Successful response"
          schema:
            type: "array"
            items:
              $ref: "#/definitions/Server"
        404:
          description: "Server not found"
    put:
      description: "Creates or updates a server"
      consumes:
        - "application/json"
      produces:
        - "application/json"
      parameters:
        - in: "body"
          name: "server"
          description: "Server object"
          required: true
          schema:
            $ref: "#/definitions/Server"
      responses:
        201:
          description: "Server created"
        204:
          description: "Server updated"
  /servers/{id}:
    delete:
      description: "Deletes a server by ID"
      produces:
        - "application/json"
      parameters:
        - name: "id"
          in: "path"
          description: "Server ID"
          required: true
          type: "string"
      responses:
        204:
          description: "Server deleted"
    get:
      description: "Returns a single server by ID"
      produces:
        - "application/json"
      parameters:
        - name: "id"
          in: "path"
          description: "Server ID"
          required: true
          type: "string"
      responses:
        200:
          description: "Successful response"
          schema:
            $ref: "#/definitions/Server"
        404:
          description: "Server not found"
  /servers/search:
    get:
      description: "Finds servers by name"
      produces:
        - "application/json"
      parameters:
        - name: "name"
          in: "query"
          description: "Server name"
          required: true
          type: "string"
      responses:
        200:
          description: "Successful response"
          schema:
            type: "array"
            items:
              $ref: "#/definitions/Server"
        404:
          description: "Server not found"
definitions:
  Server:
    type: "object"
    properties:
      id:
        type: "string"
      name:
        type: "string"
      language:
        type: "string"
      framework:
        type: "string"
    required:
      - name



java -jar swagger-codegen-cli.jar generate -i server-api.yaml -l java -o server-api




