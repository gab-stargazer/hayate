package com.lelestacia.hayate.feature.anime.core.source.local.impl

/*
 *  Genres, Explicit Genres, Demographics, and Theme have an endpoint in backend system without
 *  pagination. Therefore, it'll be queried at the initialization to reduce the heavy writing
 *  later on.
 *
 *  It will be split on 2 phase, query the Genres and Explicit Genres, then query Demographics and
 *  Themes while inserting Genres and Explicit Genres into their respective table. And after the query
 *  of Demographics and Themes finish, the app will delay for 1 second to prevent api rate limit
 *  which is 3 request per second. After 1 second passed, the screen goes normally. Initialization
 *  should only happened once, and not more. It'll also be a sample for experimenting with A/B Testing
 *  later on
 *
 *
 *
 */