package com.example.ahasolarapp.model

data class LeadListRequest(
    val token: String="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3N0YWdpbmctYWhhc29sYXItcmV3YW1wLmFoYXNvbGFyLmluL2FwaS9hdXRoL2xvZ2luIiwiaWF0IjoxNzAxOTQ1NDY4LCJleHAiOjE3MDIxMzc0NjgsIm5iZiI6MTcwMTk0NTQ2OCwianRpIjoiUG9lV2U0ZVBSdElXS1Q3TCIsInN1YiI6IjIiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.N73t5AvksTXJYV_FL0s3OIVQf27lq5JmwFIEquNg9sw",
    val search: String,
    val page: Int,
    val pageSize: Int
)
