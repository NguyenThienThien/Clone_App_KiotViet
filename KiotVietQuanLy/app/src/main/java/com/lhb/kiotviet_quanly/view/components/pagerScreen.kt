package com.lhb.kiotviet_quanly.view.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lhb.kiotviet_quanly.R
import com.lhb.kiotviet_quanly.ui.theme.OpenSans

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerScreen(pagerState: PagerState) {
    var resource = remember { 0 }
    var title = remember { "" }
    var text = remember { "" }

    Box(modifier = Modifier.fillMaxHeight()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (pagerState.currentPage) {
                        0 -> {
                            title = "Phổ biến nhất"
                            text = "Đơn giản, dễ dùng, tiết kiếm chi phí phù hợp với tất cả ngành hàng."
                            resource = R.drawable.onboarding1
                        }
                        1 -> {
                            title = "Hiệu quả nhất"
                            text = "Bán hàng dễ dàng, nhanh chóng Kiểm soát các giao dịch đầy đủ, rõ ràng"
                            resource = R.drawable.onboarding2
                        }
                        2 -> {
                            title = "Báo cáo trực quan"
                            text = "Dễ dàng theo dõi doanh thu, lợi nhuận, tồn kho, khách hàng, nhà cung cấp, ..."
                            resource = R.drawable.onboarding3
                        }
                        3 -> {
                            title = "Mọi lúc, mọi nới"
                            text = "Mọi hoạt động bán hàng, nhập hàng, trả hàng... đều được thông báo về điện thoại."
                            resource = R.drawable.onboarding4
                        }
                        4 -> {
                            title = "Hãy để"
                            text = "KiotViet đồng hành kinh doanh cùng bạn"
                            resource = R.drawable.onboarding5
                        }
                    }
                    Spacer(modifier = Modifier.padding(15.dp))
                    Image(
                        painter = painterResource(id = resource),
                        contentDescription = null,
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp)
                    )
                    Spacer(modifier = Modifier.padding(15.dp))
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.ExtraBold,
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = text,
                        fontSize = 16.sp,
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(300.dp)
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) {
                CustomIndicator(isSelected = pagerState.currentPage == it)
            }
        }
    }
}

@Composable
fun CustomIndicator(isSelected: Boolean) {
    Box(
        modifier = Modifier
            .size(13.dp)
            .background(
                color = if (isSelected) Color(0xff006ff8) else Color(0xffeaeaea),
                shape = CircleShape
            )
    )
    Spacer(modifier = Modifier.padding(5.dp))
}
