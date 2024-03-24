package com.mnj.news.newsitems

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnj.news.model.Location

@Composable
fun LocationComposable(data: Location) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp), horizontalAlignment = Alignment.Start
    ) {
        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("TP: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.ip)
            }
        })

        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Network: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.network)
            }
        })

        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Version: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.version)
            }
        })

        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("City: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.city)
            }
        })

        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Region: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.region)
            }
        })

        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Region Code: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.region_code)
            }
        })

        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Country : ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.country)
            }
        })

        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Country Name: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.country_name)
            }
        })

        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Country Code: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.country_code)
            }
        })

        Spacer(modifier = Modifier.size(10.dp))


        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Country Capital: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.country_capital)
            }
        })


        Spacer(modifier = Modifier.size(10.dp))


        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Country tld: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.country_tld)
            }
        })

        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Continent Code: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.continent_code)
            }
        })
        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("In EU: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.in_eu.toString())
            }
        })

        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Postal: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.postal)
            }
        })
        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Latitude: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.latitude.toString())
            }
        })

        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Longitude: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.longitude.toString())
            }
        })

        Spacer(modifier = Modifier.size(10.dp))


        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Timezone: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.timezone)
            }
        })

        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Ufc Offset: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.utc_offset)
            }
        })

        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Calling Code: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.country_calling_code)
            }
        })

        Spacer(modifier = Modifier.size(10.dp))


        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Currency: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.currency)
            }
        })
        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Currency Name: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.currency_name)
            }
        })

        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Languages: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.languages)
            }
        })

        /*Text(
            text = "Languages: ${data.languages}",
            style = TextStyle(
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Normal,
                fontSize = 22.sp,
                color = Color.Black
            )
        )*/

        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Country Area: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.country_area.toString())
            }
        })

        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Country Population: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.country_population.toString())
            }
        })

        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("ASN: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.asn)
            }
        })

        Spacer(modifier = Modifier.size(10.dp))

        Text(buildAnnotatedString {
            withStyle(style = LocationTextStyle.keySpanStyle) {
                append("Org: ")
            }
            withStyle(style = LocationTextStyle.valueSpanStyle) {
                append(data.org)
            }
        })
    }
}