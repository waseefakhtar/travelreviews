package com.example.travel_reviews

import com.example.travel_reviews.network.Author
import com.example.travel_reviews.network.ReviewList
import com.example.travel_reviews.network.ReviewProperty
import java.util.concurrent.ThreadLocalRandom

private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
val random
    get() = ThreadLocalRandom.current()

fun createReviewList(): ReviewList =
    ReviewList(
        reviewList = createReviewPropertyList()
    )

fun createReviewProperty(): ReviewProperty =
    ReviewProperty(
        id = randomString(),
        created = "2020-02-29T20:49:16+01:00",
        rating = randomFloat(),
        content = randomString(),
        author = createRandomAuthor()
    )

fun createReviewPropertyList(
    size: Int = positiveRandomInt(10),
    creationFunction: (Int) -> ReviewProperty = { createReviewProperty() }
): List<ReviewProperty> = (0..size).map { creationFunction(it) }

fun createRandomAuthor() =
    Author(
        fullName = randomString(),
        location = randomString(),
        avatarUrl = randomString()
    )

fun positiveRandomInt(maxInt: Int = Int.MAX_VALUE - 1): Int = random.nextInt(maxInt + 1).takeIf { it > 0 } ?: positiveRandomInt(maxInt)
fun positiveRandomLong(maxLong: Long = Long.MAX_VALUE - 1): Long = random.nextLong(maxLong + 1).takeIf { it > 0 } ?: positiveRandomLong(maxLong)
fun randomInt() = random.nextInt()
fun randomIntBetween(min: Int, max: Int) = random.nextInt(max - min) + min
fun randomLong() = random.nextLong()
fun randomFloat() = random.nextFloat()
fun randomBoolean() = random.nextBoolean()
fun randomString(size: Int = 20): String = (0..size)
    .map { charPool[random.nextInt(0, charPool.size)] }
    .joinToString()