package com.leojgp.navigationguide

var retrofit: Retrofit = Builder()
    .baseUrl("https://api.github.com/")
    .build()

var service: GitHubService = retrofit.create(GitHubService::class.java)