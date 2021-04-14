# Ping
An optimised plugin to get someone's ping, whilst using minimal server resources.

### Usage
To get your own ping:
```
/ping
```

To get someone else's ping:
```
/ping <playername>
```

### Permissions
Permission Name | Description | Default Allowed Users
--------------- | ----------- | ---------------------
`ping.all` | Parent permission, which holds `ping.use` and `ping.others`.
`ping.use` | Allows someone to use `/ping`. | `true`, which means _everyone_ can use it.
`ping.others` | Allows someone to ping another player. This permission basically allows use of the second argument in the `/ping` command. | `true`. If you don't want people seeing others' ping times, I'd recommend `op`.
