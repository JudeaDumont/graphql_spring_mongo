setup: should start normally, 

if it fails to connect to mongo, just setup mongo. 

once running, it should tell you that it loaded in resources in the GraphQL Schema

then you can run queuries against it by going to the graphiql playground.

http://localhost:8080/graphiql?path=/graphql

make sure it is enabled in application properties or you will get a 404. 

404 could mean a million different things though so look at errors in spring too.

you can run this query to get a hardcoded value out of spring

{
    schedule(coach: PHILIP) {
    id
    coach
    startsAt
    endsAt
    difficulty
    }
}

next if you wanted to, you could have your react graphql project run queries that hit this instead of its dedicatedmongo connection.
