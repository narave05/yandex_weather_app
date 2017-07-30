package narek.example.com.yandex_weather_app;


public class JsonProvider {
    private static JsonProvider instance;

    private String citiesJson = "{\"predictions\": [{\"description\": \"Москва, Россия\",\"id\": \"1a0f08fcbc047354782f00ab52e66fb56d1aadf7\",\"matched_substrings\": [{\"length\": 6,\"offset\": 0 } ],\"place_id\": \"ChIJybDUc_xKtUYRTM9XV8zWRD0\",\"reference\": \"CkQyAAAA7eQ5ECa4OJUwLSoI_X7GTgi2aIdZUeOgSmQ7ifm83gEBVJgu_QxXewNtbw6cb9iQxTSI5rG8FEEjN_sVOoULYBIQmojcyg4kYoUfpqkwozDWkRoUafJMEqVeo8MNx-1Q-7SI6RtjFk4\",\"structured_formatting\": {\"main_text\": \"Москва\",\"main_text_matched_substrings\": [{\"length\": 6,\"offset\": 0}],\"secondary_text\": \"Россия\"},\"terms\": [{\"offset\": 0,\"value\": \"Москва\"},{\"offset\": 8,\"value\": \"Россия\"}],\"types\": [\"locality\",\"political\",\"geocode\"]},{\"description\": \"Москва, Польша\",\"id\": \"e313a843c24f2a5643bdb828172875ea3e73556c\",\"matched_substrings\": [{\"length\": 6,\"offset\": 0}],\"place_id\": \"ChIJEXOLWN7NG0cRSDtN82dF1Ww\",\"reference\": \"CkQyAAAARjhpxJ9yDY28XmDiMV8ZqPxkouN4awRGLhCMFpQg9jXae7lPd5EPCjzPDB0iD7YZ03PCwOgimAv4UibnqkWXvxIQsJLj8tzwGafBEjSbcDam_xoUu3k6BOONJ1aVSvWjT9MK9VMcHHY\",\"structured_formatting\": {\"main_text\": \"Москва\",\"main_text_matched_substrings\": [{\"length\": 6,\"offset\": 0}],\"secondary_text\": \"Польша\"},\"terms\": [{\"offset\": 0,\"value\": \"Москва\"},{\"offset\": 8,\"value\": \"Польша\"}],\"types\": [\"locality\",\"political\",\"geocode\"]},{\"description\": \"Троицк, Московская область, Россия\",\"id\": \"3bbc9d955bc707908c65a344ac6a632efe4497e6\",\"matched_substrings\": [{\"length\": 6,\"offset\": 0}],\"place_id\": \"ChIJS-TmTk9VNUER1byWbctR5B4\",\"reference\": \"CmRXAAAAv2IO57ZSJzPfjbi0_6ql8G5HbIm93Az8EMUXESqLxFDa0bZcwRPvw8rdPRYXzV-rPCmCvSwFwmmk1jTRYrkXJRIDOu8aXVwLlK0N1tgiATGakUF9xN5tt3C6T7PqsgSyEhBn_X5oarlrRG5-Wkg-vTG3GhTEqsi13VDk5oohoFy831ReuyzIcw\",\"structured_formatting\": {\"main_text\": \"Троицк\",\"main_text_matched_substrings\": [{\"length\": 6,\"offset\": 0}],\"secondary_text\": \"Московская область, Россия\"},\"terms\": [{\"offset\": 0,\"value\": \"Троицк\"},{\"offset\": 8,\"value\": \"Московская область\"        },        {            \"offset\": 28,                \"value\": \"Россия\"        }            ],        \"types\": [        \"locality\",                \"political\",                \"geocode\"            ]    },    {        \"description\": \"Саларьево, Московская область, Россия\",            \"id\": \"f1fe1fe30a0c0fcece5099ba58ac82bc4ddf70f1\",            \"matched_substrings\": [        {            \"length\": 9,                \"offset\": 0        }            ],        \"place_id\": \"ChIJR6lRu_NStUYRq4SD6aOJa_0\",            \"reference\": \"CmRdAAAASikT4xz5Ep4L5h7chOWly6JuejPCVDhgA6BvV3cixBAWCKTxxwpExr-1wJlqy5lcFML20Ie_NP8H73paPx5fklN7u05oFcAt70D-rk6BG8OCXvGvsb_zvAEc_EKBuX2vEhD3icdEaZ9rc4P5pBTwK3WJGhR29szuucwpkwRhDzCANFtY1xaT6w\",            \"structured_formatting\": {     \"main_text\": \"Саларьево\",                \"main_text_matched_substrings\": [        {            \"length\": 9,                \"offset\": 0        }                ],        \"secondary_text\": \"Московская область, Россия\"    },        \"terms\": [        {            \"offset\": 0,                \"value\": \"Саларьево\"        },        {            \"offset\": 11,                \"value\": \"Московская область\"        },        {            \"offset\": 31,                \"value\": \"Россия\"        }            ],        \"types\": [        \"locality\",                \"political\",                \"geocode\"            ]    },    {        \"description\": \"Коммунарка, Россия\",            \"id\": \"8276b6462fd1f22e69b6479a806622a78c073102\",            \"matched_substrings\": [        {            \"length\": 10,                \"offset\": 0        }            ],        \"place_id\": \"ChIJD9s7IeisSkERVyaPm1VM310\",            \"reference\": \"CkQ6AAAAMCqJnlk_VhQDY1JcAqlBwMYEZZCp62wcoAJ9_hzG5B-V5ruKR0fgJWpShYSnoBO1egHfZ3rmhL-6-14U2vO6CRIQ_E8SXtLhWmomQkwPvprp6xoUcmDhhUx7KKgjOKNKKrbzKtSosY0\",            \"structured_formatting\": {        \"main_text\": \"Коммунарка\",                \"main_text_matched_substrings\": [        {            \"length\": 10,                \"offset\": 0        }                ],        \"secondary_text\": \"Россия\"    },        \"terms\": [        {            \"offset\": 0,                \"value\": \"Коммунарка\"        },        {            \"offset\": 12,                \"value\": \"Россия\"        }            ],        \"types\": [        \"locality\",                \"political\",                \"geocode\"            ]    }    ],            \"status\": \"OK\"}";
    private String coordsJson = "{\n" +
            "    \"html_attributions\": [],\n" +
            "    \"result\": {\n" +
            "        \"address_components\": [\n" +
            "            {\n" +
            "                \"long_name\": \"Moscow\",\n" +
            "                \"short_name\": \"Moscow\",\n" +
            "                \"types\": [\n" +
            "                    \"locality\",\n" +
            "                    \"political\"\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"long_name\": \"Moskva\",\n" +
            "                \"short_name\": \"Moskva\",\n" +
            "                \"types\": [\n" +
            "                    \"administrative_area_level_2\",\n" +
            "                    \"political\"\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"long_name\": \"Russia\",\n" +
            "                \"short_name\": \"RU\",\n" +
            "                \"types\": [\n" +
            "                    \"country\",\n" +
            "                    \"political\"\n" +
            "                ]\n" +
            "            }\n" +
            "        ],\n" +
            "        \"adr_address\": \"<span class=\\\"locality\\\">Moscow</span>, <span class=\\\"country-name\\\">Russia</span>\",\n" +
            "        \"formatted_address\": \"Moscow, Russia\",\n" +
            "        \"geometry\": {\n" +
            "            \"location\": {\n" +
            "                \"lat\": 55.755826,\n" +
            "                \"lng\": 37.6173\n" +
            "            },\n" +
            "            \"viewport\": {\n" +
            "                \"northeast\": {\n" +
            "                    \"lat\": 56.009657,\n" +
            "                    \"lng\": 37.9456611\n" +
            "                },\n" +
            "                \"southwest\": {\n" +
            "                    \"lat\": 55.48992699999999,\n" +
            "                    \"lng\": 37.3193289\n" +
            "                }\n" +
            "            }\n" +
            "        },\n" +
            "        \"icon\": \"https://maps.gstatic.com/mapfiles/place_api/icons/geocode-71.png\",\n" +
            "        \"id\": \"1a0f08fcbc047354782f00ab52e66fb56d1aadf7\",\n" +
            "        \"name\": \"Moscow\",\n" +
            "        \"photos\": [\n" +
            "            {\n" +
            "                \"height\": 3869,\n" +
            "                \"html_attributions\": [\n" +
            "                    \"<a href=\\\"https://maps.google.com/maps/contrib/103291604560917117840/photos\\\">鍾懷亞</a>\"\n" +
            "                ],\n" +
            "                \"photo_reference\": \"CmRaAAAAw0UI68Nkcjl_EsK3BqDxwVTeFfADjG-JuwDzyXn0o3tn_Fwu5iQPI7V5CiBp5OkiS1H6_HRK7y8Ewgd_KDSP4HKxkU0RpT28QgLc49qsMb7u80DvDejv_IL7RmL8i1nqEhA1rw-Bpu4FDGLLBaiOMBypGhTK7YC6Os5YYLjUOxcdLTCO8-GDeQ\",\n" +
            "                \"width\": 5803\n" +
            "            },\n" +
            "            {\n" +
            "                \"height\": 2160,\n" +
            "                \"html_attributions\": [\n" +
            "                    \"<a href=\\\"https://maps.google.com/maps/contrib/104489255406387162989/photos\\\">Сергей Шмаков</a>\"\n" +
            "                ],\n" +
            "                \"photo_reference\": \"CmRaAAAAsuMUs0ffBZakNyUusnRArKL14yH1YM1u-mYxmaTftyXl82GJbMRtbd6thTfJnTM3EothXHDLBzKwPyjlw3UdJlpt71KQp5Qo38SCMKZELoprxEk-rJhOXvbUdSvuWsVAEhBnfAtOwidMy1xpCRuDtboDGhQA5ewqvdlJHWOhOMJaMtEsINdsMA\",\n" +
            "                \"width\": 3840\n" +
            "            },\n" +
            "            {\n" +
            "                \"height\": 1333,\n" +
            "                \"html_attributions\": [\n" +
            "                    \"<a href=\\\"https://maps.google.com/maps/contrib/107956383758290156645/photos\\\">Флотилия &quot;Рэдиссон Ройал, Москва&quot;</a>\"\n" +
            "                ],\n" +
            "                \"photo_reference\": \"CmRaAAAA43NVUrKTwy4pWGft6cPhdiTC3C6gUF0FVd3IXIwRO4gWMG8O_GJ9b-bW8ieHq283Ntn3HD61wY6DVhoG62-77gr7SI_XWglRn2YGPTPiaiIaHWQwx5ln8w6VGyptFk4eEhCZlZv_LRJYieBvXgF9DAzTGhTP9f754Zbb4hOIFmM1eyItf7tq8g\",\n" +
            "                \"width\": 2000\n" +
            "            },\n" +
            "            {\n" +
            "                \"height\": 2160,\n" +
            "                \"html_attributions\": [\n" +
            "                    \"<a href=\\\"https://maps.google.com/maps/contrib/114911699120710775872/photos\\\">PeriscopeLiveCorr</a>\"\n" +
            "                ],\n" +
            "                \"photo_reference\": \"CmRaAAAAUemeUDIvIgdL1RL3Zb4J3v3a8iqKD8cSVcAAlcDBe4C5OkXUS0ddv6oa8a9q2Bp9L4EumnoearqrNRu4j48lY9w6NmhlIwT0TaQ3ILzc8-Rpw-IwPw0hxaLZHyp1q6TcEhBdy-QDzPJE9MgmlilqGbM6GhTkM4rzjlz0_W-qj0LqVB636PN65g\",\n" +
            "                \"width\": 3840\n" +
            "            },\n" +
            "            {\n" +
            "                \"height\": 2988,\n" +
            "                \"html_attributions\": [\n" +
            "                    \"<a href=\\\"https://maps.google.com/maps/contrib/105260961063985950130/photos\\\">Ekaterina Rodkina</a>\"\n" +
            "                ],\n" +
            "                \"photo_reference\": \"CmRaAAAAkiMm2ZhEjaZPDauCZ0zBAU2aVg8nLX2qvj6OP5Q4_iEcGC7m8L0Kv1BYrmUsc0Dd5p-eQNFR84VB2fwKuqaO8N3jUYRmLWHPx4frxeG3umogsnlEWLedaeeoCqSKEZo-EhAV1tQbQV7VMQjSsCK4zHDqGhRzxFRMw1_cU5HE1QLGt4_L8ztw6w\",\n" +
            "                \"width\": 5312\n" +
            "            },\n" +
            "            {\n" +
            "                \"height\": 1836,\n" +
            "                \"html_attributions\": [\n" +
            "                    \"<a href=\\\"https://maps.google.com/maps/contrib/117387643646577133592/photos\\\">Алла Цветкова</a>\"\n" +
            "                ],\n" +
            "                \"photo_reference\": \"CmRaAAAAjOjMQesVxgYsFxghISBoe-UA6CyWnuN8OO3PaB_XmWgVHsR4fGtNjozQkIDIzam0DJttCegDAgxQV7Wll3epnsixiZz3QLPHE2ZKVKsEJr7uU4HH1-BG003kirGzsJlPEhBgYpyubXBiDpo4aQPyj57eGhSSxPnaqPRQDcqpiJ6Xhf1FchqZSw\",\n" +
            "                \"width\": 3264\n" +
            "            },\n" +
            "            {\n" +
            "                \"height\": 1536,\n" +
            "                \"html_attributions\": [\n" +
            "                    \"<a href=\\\"https://maps.google.com/maps/contrib/109441349542816639937/photos\\\">Mack The Knife</a>\"\n" +
            "                ],\n" +
            "                \"photo_reference\": \"CmRaAAAArAePqXKyCh9qeVwomyl2xG42eglctrzOuqg5vgWKgs7JAs_PS2tNtGrQs7VMc8uFg3pKY1kFX5jUMEfQhRlU1-FNOHlznrrmfykL8n5jXI74TUlr-5adUxOc5WT2-C_QEhA2c-cFyQ3YlhJHvvILarTXGhSy4190kMy7dlJeqrVHE8zJLUh8KA\",\n" +
            "                \"width\": 2048\n" +
            "            },\n" +
            "            {\n" +
            "                \"height\": 1333,\n" +
            "                \"html_attributions\": [\n" +
            "                    \"<a href=\\\"https://maps.google.com/maps/contrib/107956383758290156645/photos\\\">Флотилия &quot;Рэдиссон Ройал, Москва&quot;</a>\"\n" +
            "                ],\n" +
            "                \"photo_reference\": \"CmRaAAAAg3KHNAzuEuvdymGKZxF2tIbh3UntE_drg54D3OxCICNDZn0pagZgGBRp1yIIYm782GJagpiIpZpsnFmqTTj_v_GcNn6KllXbC20U9ynYEqH0zkcpWZPxfKQu_0BxhtA9EhBaB731IWEC9iSTRISPojVZGhS9-OaV78bqwT0MmTiLZxAnTSKcbQ\",\n" +
            "                \"width\": 2000\n" +
            "            },\n" +
            "            {\n" +
            "                \"height\": 1440,\n" +
            "                \"html_attributions\": [\n" +
            "                    \"<a href=\\\"https://maps.google.com/maps/contrib/104293230979374021827/photos\\\">Кристина Овечкина</a>\"\n" +
            "                ],\n" +
            "                \"photo_reference\": \"CmRaAAAA4QEUJKHypkl-jDoe8FXnVRnKlgPIZTE_AtSbHizrGS1RugXf9Cb6OKOZZ0EfM5g9NjoN2xqmgrD38ha8gI1-vIYQ-EmXxlJt3yM3E41JAHP002Na7ESkHAnZZjl2Qfr-EhBJlD5-yJjtrbtCu7zBkTBLGhSnPTWxHIlbFOmQbCfxTBD0oLcjFA\",\n" +
            "                \"width\": 2560\n" +
            "            },\n" +
            "            {\n" +
            "                \"height\": 1365,\n" +
            "                \"html_attributions\": [\n" +
            "                    \"<a href=\\\"https://maps.google.com/maps/contrib/116744817591115149507/photos\\\">Rizvan Gereykhanov</a>\"\n" +
            "                ],\n" +
            "                \"photo_reference\": \"CmRaAAAAJ_8EQaQNaX07aRkDOuzpluHKUd2-cwKJ2lVZscUw7gYasBk3gcsEhXDYd-UJewbozEr03upKWAmoQjGvXTVvimbcopTFSK-d7thq-2a6PBmDv6JiUbxdNzgFhC1G_BUXEhDlo5ePVOS1XG-EE3G6RcMkGhTEBeD41TdL2UR3dFiok-eACFn0pw\",\n" +
            "                \"width\": 2048\n" +
            "            }\n" +
            "        ],\n" +
            "        \"place_id\": \"ChIJybDUc_xKtUYRTM9XV8zWRD0\",\n" +
            "        \"reference\": \"CmRbAAAAgbFP1gUvGxlFkMotfHCcnkQAMpCL62_xu2WCT48QsOwr2O0kVvqU_57AFcAaK136Sl1_AsRC52mfT-YLrXplomNSSNULwYtfXgJaE-IG0tkM_gg_qtJL5HSuTvRvCcxhEhDE_RtISq3SZEgtJn0-RSW_GhQn-hHbXdCyYnEzcl8wTAx2cZzULw\",\n" +
            "        \"scope\": \"GOOGLE\",\n" +
            "        \"types\": [\n" +
            "            \"locality\",\n" +
            "            \"political\"\n" +
            "        ],\n" +
            "        \"url\": \"https://maps.google.com/?q=Moscow,+Russia&ftid=0x46b54afc73d4b0c9:0x3d44d6cc5757cf4c\",\n" +
            "        \"utc_offset\": 180,\n" +
            "        \"vicinity\": \"Moscow\"\n" +
            "    },\n" +
            "    \"status\": \"OK\"\n" +
            "}";

    private String weatherJson = "{\"coord\": {\"lon\": 37.62,\"lat\": 55.76},\"weather\": [{\"id\": 800,\"main\": \"Clear\",\"description\": \"clear sky\",\"icon\": \"01n\"}],\"base\": \"stations\",\"main\": {\"temp\": 295.42,\"pressure\": 1005,\"humidity\": 78,\"temp_min\": 295.15,\"temp_max\": 296.15},\"visibility\": 10000,\"wind\": {\"speed\": 2,\"deg\": 130},\"clouds\": {\"all\": 0},\"dt\": 1501363800,\"sys\": {\"type\": 1,\"id\": 7323,\"message\": 0.0024,\"country\": \"RU\",\"sunrise\": 1501291853,\"sunset\": 1501349987},\"id\": 524925,\"name\": \"Moskovskaya Oblast’\",\"cod\": 200}";

    private JsonProvider() {
    }

    public static JsonProvider getInstance(){
        if (instance == null) {
            instance = new JsonProvider();
        }
        return instance;
    }

    public String getCitiesJson() {
        return citiesJson;
    }

    public String getCoordsJson() {
        return coordsJson;
    }


    public String getWeatherJson() {
        return weatherJson;
    }

}
