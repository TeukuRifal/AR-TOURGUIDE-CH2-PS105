package com.capstone.ar_tourguide.ui.camera

import android.os.Bundle
import android.widget.Toast
import com.dewakoding.arlocationbased.model.Place
import com.dewakoding.arlocationbased.ui.ARActivity


class CameraAR : ARActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val list = ArrayList<Place>()
        list.add(
            Place("1",
                "Museum Nasional Indonesia",
                -6.176231422177547,
                106.82162228554326,
                description="Museum Nasional Indonesia, atau yang sering disebut dengan Museum Gajah, adalah sebuah museum arkeologi, sejarah, etnografi, dan geografi yang terletak di Jakarta Pusat.",
                photoUrl ="https://storage.googleapis.com/artourguide/DESTINASI/Museum%20Nasional%20Indonesia.jpg")
        )
        list.add(
            Place("2",
                "Lapangan Banteng",
                -6.170362987074772,
                106.83503779875186,
                description="Lapangan Banteng adalah lapangan bersejarah yang terletak di kawasan bersejarah yang dulunya dikenal dengan nama Weltevreden.",
                photoUrl = "https://storage.googleapis.com/artourguide/DESTINASI/Lapangan%20Banteng.jpg")
        )
        list.add(
            Place("3",
                "Mesjid Al Istiqlal",
                -6.170064995373983,
                106.8312617905345,
                description="Masjid ini menjadi masjid terbesar di Asia Tenggara dan masjid terbesar keenam di dunia dalam hal kapasitas jamaah.",
                photoUrl = "https://storage.googleapis.com/artourguide/DESTINASI/Mesjid%20Al%20Istiqlal.jpg")
        )
        list.add(
            Place("4",
                "Galeri Nasional Indonesia",
                -6.178281524254377,
                106.83276086952438,
                description="Museum di sebuah bangunan tahun 1817 yang menampilkan pameran permanen & bergilir oleh seniman lokal & internasional.",
                photoUrl = "https://storage.googleapis.com/artourguide/DESTINASI/Galeri%20Nasional%20Indonesia.jpg")
        )
        list.add(
            Place("5",
                "Gereja Katedral Jakarta",
                -6.169168986582914,
                106.83272091449251,
                description="Gereja Katedral Jakarta, juga dikenal sebagai Katedral Santo Maria Pelindung Diangkat ke Surga, dibangun pada tahun 1901 dan menjadi pusat ibadah Katolik yang penting di Indonesia.",
                photoUrl = "https://storage.googleapis.com/artourguide/DESTINASI/Gereja%20Katedral%20Jakarta.jpg")
        )
        list.add(
            Place("6",
                "Perpustakaan Nasional RI",
                -6.180371290687305,
                106.8269632599379,
                description="Perpustakaan Nasional Indonesia adalah perpustakaan deposit resmi di Indonesia.",
                photoUrl = "https://storage.googleapis.com/artourguide/DESTINASI/Perpustakaan%20Nasional%20RI.jpg")
        )
        list.add(
            Place("7",
                "Tugu Tani",
                -6.181501454014754,
                106.8331150046826,
                description="Tugu Tani, dibangun pada tahun 1963, adalah monumen yang menghormati peran petani dalam pembangunan negara. Tugu ini menjadi simbol keberanian dan ketahanan rakyat Indonesia.",
                photoUrl = "https://storage.googleapis.com/artourguide/DESTINASI/Tugu%20Tani.jpg")
        )
        list.add(
            Place("8",
                "Museum Prasasti Jakarta",
                -6.170814187569505,
                106.81971966562091,
                description="Museum Taman Prasasti adalah sebuah museum yang terletak di Jakarta, Indonesia.",
                photoUrl = "https://storage.googleapis.com/artourguide/DESTINASI/Museum%20Prasasti%20Jakarta.jpg")
        )
        list.add(
            Place("9",
                "Museum Kebangkitan Nasional",
                -6.178027619882558,
                106.83805928128204,
                description="Museum Kebangkitan Nasional adalah sebuah museum sejarah di Jakarta, Indonesia.",
                photoUrl = "https://storage.googleapis.com/artourguide/DESTINASI/Museum%20Kebangkitan%20Nasional.jpg")
        )
        list.add(
            Place("10",
                "Gedung Sarinah",
                -6.187517427482174,
                106.82366150083098,
                description="PT Sarinah adalah perusahaan ritel Indonesia yang dimiliki oleh PT Aviasi Pariwisata Indonesia.",
                photoUrl = "https://storage.googleapis.com/artourguide/DESTINASI/Gedung%20Sarinah.jpg")
        )
        list.add(
            Place("11",
                "Masjid Baiturrahman",
                5.553806015924622,
                95.31725281402024,
                description="Masjid Baiturrahman adalah masjid utama di Banda Aceh dan merupakan salah satu masjid tertua dan paling terkenal di Indonesia. Masjid ini dibangun pada tahun 1612 oleh Sultan Iskandar Muda selama masa Kesultanan Aceh. Masjid ini memiliki peran sejarah dalam perjuangan rakyat Aceh melawan penjajah Belanda. Saat itu, masjid ini digunakan sebagai benteng pertahanan dan tempat perundingan. Masjid Baiturrahman menjadi simbol keagamaan dan kebanggaan masyarakat Aceh.",
                photoUrl = "https://www.djkn.kemenkeu.go.id/files/images/2022/11/Salinan_dari_KPKNL_BANDA_ACEH1.jpg")
        )
        list.add(
            Place("12",
                "Pantai Lampuuk",
                5.486337478368117,
                95.22636266942028,
                description="Pantai Lampuuk adalah salah satu pantai indah di Aceh yang terkenal dengan keindahan pasir putihnya. Pantai ini berlokasi di Kecamatan Lhoknga, sekitar 15 kilometer dari pusat kota Banda Aceh. Pantai ini merupakan destinasi populer bagi wisatawan yang mencari keindahan alam dan ketenangan.",
                photoUrl = "https://wisatakita.com/pariwisata/540/800-Pantai-Lampuuk.jpeg")
        )
        list.add(
            Place("13",
                "Museum Tsunami Aceh",
                5.548603596521812,
                95.31473427187728,
                description="Museum Tsunami Aceh didirikan sebagai peringatan dan penghormatan bagi korban Tsunami 2004 di Aceh. Museum ini berisi pameran tentang kejadian Tsunami 2004 dan upaya rekonstruksi yang dilakukan oleh masyarakat Aceh.",
                photoUrl = "https://exov.sgp1.digitaloceanspaces.com/assets/img/pilihan/museum-tsunami-aceh_1.webp")
        )
        list.add(
            Place("14",
                "Monumen Nasional Indonesia ",
                -6.175612757966026,
                106.82716909086115,
                description="Monumen Nasional, atau yang lebih dikenal sebagai Monas, adalah salah satu simbol kebanggaan Indonesia. Monas dibangun untuk memperingati perjuangan rakyat Indonesia dalam merebut kemerdekaan. Monumen ini juga menjadi ikon Jakarta dan destinasi wisata populer.",
                photoUrl = "https://jakarta-tourism.sgp1.cdn.digitaloceanspaces.com/articles/KJSccjOeJliS0ci0kvKHNVxWROmzwG-metaTW9uYXNfMzIuanBn-.jpg")
        )
        // You want to display places within a radius of 50 meters.
        ARInitData(list, 5000000.00)
    }

    override fun onARPointSelected(place: Place) {
        Toast.makeText(applicationContext, place.name, Toast.LENGTH_SHORT).show()
    }
}