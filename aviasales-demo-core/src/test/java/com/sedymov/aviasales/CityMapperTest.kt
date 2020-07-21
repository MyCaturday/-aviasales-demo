package com.sedymov.aviasales

import com.sedymov.aviasales.core.mappers.search.cities.CityMapper
import com.sedymov.aviasales.core.models.search.CitiesResponse
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.models.search.Location
import junit.framework.Assert.assertEquals
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CityMapperTest {

    private lateinit var cityMapper: CityMapper

    @Before
    fun setUp() {
        cityMapper = CityMapper()
    }

    @Test(expected = Test.None::class)
    fun testPublishingStartCity() {

        val locationMock = Mockito.mock(Location::class.java)
        Mockito.`when`(locationMock.lat).thenReturn(0.14)
        Mockito.`when`(locationMock.lon).thenReturn(0.88)

        val cityMock = Mockito.mock(City::class.java)
        Mockito.`when`(cityMock.city).thenReturn("Омск")
        Mockito.`when`(cityMock.location).thenReturn(locationMock)
        Mockito.`when`(cityMock.iata).thenReturn(listOf("OMS"))

        val responseMock = Mockito.mock(CitiesResponse::class.java)
        Mockito.`when`(responseMock.cities).thenReturn(listOf(cityMock))

        val result = cityMapper.toModels(responseMock)

        assertThat(result).containsOnly(cityMock)

        with(result[0]) {

            assertEquals("Омск", city)
            assertThat(iata).containsOnly("OMS")

            with(location) {

                assertEquals(0.14, lat)
                assertEquals(0.88, lon)
            }
        }
    }
}