theme: /

    state: newNode_0
        random:
            a: проверкин в девелопе
            a: проверкин
            a: Вы хотите создать новый рецепт или посмотреть меню?
            a: Посмотрите рецепты или запишете новый?
            a: Выбрать рецепт или создать новый?
        image: https://content.sberdevices.ru/smartmarket-smide-prod/1624/1625/YccyYAjc4BpsQVN3.jpg || hash = "743099ff38fd656f9ea9395c13d38c13"
        go!: /newNode_8
    @IntentGroup
        {
          "boundsTo" : "/newNode_0",
          "actions" : [ {
            "buttons" : [ {
              "name" : "Создать",
              "transition" : "/newNode_20"
            }, {
              "name" : "Посмотреть",
              "transition" : "/newNode_18"
            }, {
              "name" : "Конвертер",
              "transition" : "/newNode_35"
            }, {
              "name" : "Посоветуй своё",
              "transition" : "/newNode_28"
            } ],
            "type" : "buttons"
          } ],
          "global" : true
        }
    state: newNode_8
        state: 1
            e!: Начальный экран
            e!: В начало
            q!: * начал*

            go!: /newNode_0

        state: 2
            e!: Покажи все рецепты
            e!: Все рецепты

            go!: /newNode_13

        state: 3
            e!: Предложи своё любимое

            go!: /newNode_28

        state: 4
            e!: конвертер

            go!: /newNode_35
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_8",
                name: "newNode_8 buttons",
                handler: function($context) {
                  $reactions.buttons([
                    {text: "Создать"
                    , transition: "/newNode_20"
                    },
                    {text: "Посмотреть"
                    , transition: "/newNode_18"
                    },
                    {text: "Конвертер"
                    , transition: "/newNode_35"
                    },
                    {text: "Посоветуй своё"
                    , transition: "/newNode_28"
                    },
                  ]);
                }
            });

    state: newNode_28
        if: $session.rawRequest.payload.character.name === "Сбер"
            go!: /newNode_32
        elseif: $session.rawRequest.payload.character.name === "Джой"
            go!: /newNode_33
        elseif: $session.rawRequest.payload.character.name === "Афина"
            go!: /newNode_34

    state: newNode_18
        HttpRequest:
            url = https://smartapp-code.sberdevices.ru/tools/api/now?tz=Europe/Moscow&format=dd/MM/yyyy
            method = GET
            body = 
            okState = /newNode_24
            errorState = 
            timeout = 5000
            headers = []
            vars = [{"name":"hour","value":"$httpResponse.hour"}]

    state: newNode_24
        a: Времени {{$session.hour}}
        if: (6 <= $session.hour) && ($session.hour < 12)
            go!: /newNode_5
        elseif: (12 <= $session.hour) && ($session.hour < 18)
            go!: /newNode_7
        elseif: (18 <= $session.hour) && ($session.hour < 24)
            go!: /newNode_25

    state: newNode_32
        a: Это выглядит очень интересно, на мой взгляд
        # Transition /newNode_49
        go!: /newNode_39

    state: newNode_33
        a: Я бы выбрала это
        # Transition /newNode_47
        go!: /newNode_46

    state: newNode_34
        a: Это блюдо выглядит наиболее полезным
        # Transition /newNode_48
        go!: /newNode_45

    state: newNode_5
        a: Кажется у вас ещё утро, хотите посмотреть рецепты завтраков?
        go!: /newNode_11
    @IntentGroup
        {
          "boundsTo" : "/newNode_5",
          "actions" : [ {
            "buttons" : [ ],
            "type" : "buttons"
          } ],
          "global" : false
        }
    state: newNode_11
        state: 1
            q: $AGREEMENT

            go!: /newNode_12

        state: 2
            q: $NEGATION

            go!: /newNode_13
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_11",
                name: "newNode_11 buttons",
                handler: function($context) {
                }
            });

    state: newNode_7
        a: День в самом разгаре, хотите посмотреть рецепты обедов?
        go!: /newNode_14
    @IntentGroup
        {
          "boundsTo" : "/newNode_7",
          "actions" : [ {
            "buttons" : [ ],
            "type" : "buttons"
          } ],
          "global" : false
        }
    state: newNode_14
        state: 1
            q: $AGREEMENT

            go!: /newNode_16

        state: 2
            q: $NEGATION

            go!: /newNode_13
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_14",
                name: "newNode_14 buttons",
                handler: function($context) {
                }
            });

    state: newNode_25
        a: У вас сейчас {{$session.hour}} часов. Вам могли бы быть интересны рецепты ужинов?
        go!: /newNode_26
    @IntentGroup
        {
          "boundsTo" : "/newNode_25",
          "actions" : [ {
            "buttons" : [ ],
            "type" : "buttons"
          } ],
          "global" : false
        }
    state: newNode_26
        state: 1
            q: $AGREEMENT

            go!: /newNode_27

        state: 2
            q: $NEGATION

            go!: /newNode_13
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_26",
                name: "newNode_26 buttons",
                handler: function($context) {
                }
            });

    state: newNode_12
        a: Меню завтраков

    state: newNode_16
        a: Меню обедов

    state: newNode_27
        a: Меню ужинов

    state: newNode_13
        a: Вот весь список рецептов
        go!: /newNode_37

    state: newNode_37
        CardList:
            actions = [{"buttons":[],"type":"buttons"}]
            listTitle = Список всех рецептов
            listItems = [{"title":"Шакшука","value":"","subtitle":"","iconUrl":"","hash":"","action":{"then":"/newNode_39","name":"Шакшука"}},{"title":"Сырники","value":"","subtitle":"","iconUrl":"","hash":"","action":{"then":"/newNode_40","name":"Сырники"}},{"title":"Бефстроганов","value":"","subtitle":"","iconUrl":"","hash":"","action":{"then":"/newNode_43","name":"Бефстроганов"}},{"title":"Борщ","value":"","subtitle":"","iconUrl":"","hash":"","action":{"then":"/newNode_44","name":"Борщ"}},{"title":"Овощной салат","value":"","subtitle":"","iconUrl":"","hash":"","action":{"then":"/newNode_45","name":"Овощной салат"}},{"title":"Сэндвич BLT","value":"","subtitle":"","iconUrl":"","hash":"","action":{"then":"/newNode_46","name":"Сэндвич BLT"}}]
            button = {"transition":"","text":"","enabled":false}

    state: newNode_39
        SberCard:
            actions = [{"buttons":[],"type":"buttons"}]
            imageUrl = https://content.sberdevices.ru/smartmarket-smide-prod/1624/1625/sCucan1xGBuv2QCk.png
            hash = 8349610bc6c72f7a816210e7392c6d42
            button = {"transition":"","text":""}
            cardTitle = Шакшука
            description = Ближневосточный хит

    state: newNode_40
        SberCard:
            actions = [{"buttons":[],"type":"buttons"}]
            imageUrl = 
            hash = 
            button = {"transition":"","text":""}
            cardTitle = Сырники
            description = Вкусный и питательный завтрак

    state: newNode_43
        SberCard:
            actions = [{"buttons":[],"type":"buttons"}]
            imageUrl = 
            hash = 
            button = {"transition":"","text":""}
            cardTitle = Бефстроганов
            description = Картофельное пюре с говядиной в сливочном соусе

    state: newNode_44
        SberCard:
            actions = [{"buttons":[],"type":"buttons"}]
            imageUrl = 
            hash = 
            button = {"transition":"","text":""}
            cardTitle = Борщ
            description = Популярный свекольный суп. Лучше всего подавать со сметаной и зубчиком чеснока.

    state: newNode_45
        SberCard:
            actions = [{"buttons":[],"type":"buttons"}]
            imageUrl = 
            hash = 
            button = {"transition":"","text":""}
            cardTitle = Овощной салат
            description = Легкий ужин после шести.

    state: newNode_46
        SberCard:
            actions = [{"buttons":[],"type":"buttons"}]
            imageUrl = 
            hash = 
            button = {"transition":"","text":""}
            cardTitle = Сэндвич BLT
            description = Зарубежная классика сэндвичей.

    state: newNode_20
        InputText:
            actions = [{"buttons":[],"type":"buttons"}]
            prompt = Назовите свое блюдо
            varName = dishName
            then = /newNode_29

    state: newNode_29
        a: На сколько людей рассчитан рецепт?
        go!: /newNode_30
    @IntentGroup
        {
          "boundsTo" : "/newNode_29",
          "actions" : [ {
            "buttons" : [ ],
            "type" : "buttons"
          } ],
          "global" : false
        }
    state: newNode_30
        state: 1
            e: $NUMBER::numberOfPeople

            go!: /newNode_31
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_30",
                name: "newNode_30 buttons",
                handler: function($context) {
                }
            });

    state: newNode_31
        a: Вы создали рецепт {{$session.newRecipe}}, рассчитанный на {{$session.numberOfPeople}} персон
        a: Сохранить рецепт?
        go!: /newNode_50
    @IntentGroup
        {
          "boundsTo" : "/newNode_31",
          "actions" : [ {
            "buttons" : [ ],
            "type" : "buttons"
          } ],
          "global" : false
        }
    state: newNode_50
        state: 1
            q: $AGREEMENT

            go!: /newNode_51

        state: 2
            q: $NEGATION

            go!: /
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_50",
                name: "newNode_50 buttons",
                handler: function($context) {
                }
            });

    state: newNode_51
        HttpRequest:
            url = https://smartapp-code.sberdevices.ru/tools/api/data/$userId
            method = POST
            body = {
                "recipe1": {
                "name": "{{$session.dishName}}",
                "numberOfPeople": {{$session.numberOfPeople}}
                }
                }
            okState = /newNode_52
            errorState = /newNode_53
            timeout = 5000
            headers = []
            vars = []

    state: newNode_52
        a: Рецепт сохранён успешно
        # Transition /newNode_54
        go!: /newNode_0

    state: newNode_53
        a: Сохранить рецепт не удалось
        # Transition /newNode_55
        go!: /newNode_0

    state: newNode_35
        script:
            $response.replies = $response.replies || [];
            $response.replies.push({
                    "type": "raw",
                    "body": {
                        "emotion": null,
                        "items": [{
                            "card": {
                                "type": "grid_card",
                                "items": [
                                    {
                                        "type": "greeting_grid_item",
                                        "top_text": {
                                            "type": "text_cell_view",
                                            "text": "1 унция",
                                            "typeface": "caption",
                                            "text_color": "default",
                                            "max_lines": 3
                                        },
                                        "bottom_text": {
                                            "type": "text_cell_view",
                                            "text": "28 грамм",
                                            "typeface": "body3",
                                            "text_color": "default",
                                            "max_lines": 3,
                                            "margins": {
                                                "top": "4x"
                                            }
                                        },
                                        "paddings": {
                                            "top": "6x",
                                            "left": "6x",
                                            "right": "6x",
                                            "bottom": "6x"
                                        }
                                    },
                                    {
                                        "type": "greeting_grid_item",
                                        "top_text": {
                                            "type": "text_cell_view",
                                            "text": "1 фунт",
                                            "typeface": "caption",
                                            "text_color": "default",
                                            "max_lines": 3
                                        },
                                        "bottom_text": {
                                            "type": "text_cell_view",
                                            "text": "16 унций\n 454 грамма",
                                            "typeface": "body3",
                                            "text_color": "default",
                                            "max_lines": 3,
                                            "margins": {
                                                "top": "4x"
                                            }
                                        },
                                        "paddings": {
                                            "top": "6x",
                                            "left": "6x",
                                            "right": "6x",
                                            "bottom": "6x"
                                        }
                                    },
                                    {
                                        "type": "greeting_grid_item",
                                        "top_text": {
                                            "type": "text_cell_view",
                                            "text": "1 ст. ложка",
                                            "typeface": "caption",
                                            "text_color": "default",
                                            "max_lines": 3
                                        },
                                        "bottom_text": {
                                            "type": "text_cell_view",
                                            "text": "3 чайные ложки",
                                            "typeface": "body3",
                                            "text_color": "default",
                                            "max_lines": 3,
                                            "margins": {
                                                "top": "4x"
                                            }
                                        },
                                        "paddings": {
                                            "top": "6x",
                                            "left": "6x",
                                            "right": "6x",
                                            "bottom": "6x"
                                        }
                                    },
                                    {
                                        "type": "greeting_grid_item",
                                        "top_text": {
                                            "type": "text_cell_view",
                                            "text": "1 кварта",
                                            "typeface": "caption",
                                            "text_color": "default",
                                            "max_lines": 3
                                        },
                                        "bottom_text": {
                                            "type": "text_cell_view",
                                            "text": "4 чашки\n16 ст. ложек\n950 мл",
                                            "typeface": "body3",
                                            "text_color": "default",
                                            "max_lines": 3,
                                            "margins": {
                                                "top": "4x"
                                            }
                                        },
                                        "paddings": {
                                            "top": "6x",
                                            "left": "6x",
                                            "right": "6x",
                                            "bottom": "6x"
                                        }
                                    }
                                ],
                                "columns": 2,
                                "item_width": "small"
                            }
                        }]
                    }
            });
        a: Что вы хотите перевести?
        buttons:
            "Унции" -> /newNode_56
            "Кварты" -> /newNode_58
            "Фаренгейты" -> /newNode_59

    state: newNode_56
        InputNumber:
            actions = [{"buttons":[],"type":"buttons"}]
            prompt = Сколько унций перевести
            varName = ounces
            failureMessage = []
            then = /newNode_38
            minValue = 1
            maxValue = 10000

    state: newNode_58
        InputNumber:
            actions = [{"buttons":[],"type":"buttons"}]
            prompt = Сколько кварт перевести?
            varName = quart
            failureMessage = []
            then = /newNode_57
            minValue = 1
            maxValue = 10000

    state: newNode_59
        InputNumber:
            actions = [{"buttons":[],"type":"buttons"}]
            prompt = Сколько градусов фаренгейта перевести?
            varName = fahrenheit
            failureMessage = []
            then = /newNode_63
            minValue = 1
            maxValue = 10000

    state: newNode_38
        script:
            function convertWeight(weight) {
               return weight/0.035274;
            };
            $session.grams = convertWeight($session.ounces);
        # Transition /newNode_41
        go!: /newNode_42

    state: newNode_57
        script:
            function convertVolume(volume) {
               return volume*946.35;
            };
            $session.ml = convertVolume($session.quart);
        # Transition /newNode_62
        go!: /newNode_60

    state: newNode_63
        script:
            function convertTemp(temperature) {
               return (temperature-32)/1.8;
            };
            $session.celsius = convertTemp($session.fahrenheit);
        # Transition /newNode_64
        go!: /newNode_61

    state: newNode_42
        a: {{$session.ounces}} унций это {{$session.grams}} в граммах
        # Transition /newNode_65
        go!: /newNode_35

    state: newNode_60
        a: {{$session.quart}} в квартах это {{$session.ml}} в миллилитрах
        # Transition /newNode_66
        go!: /newNode_35

    state: newNode_61
        a: {{$session.fahrenheit}} в фаренгейтах это {{$session.celsius}} в цельсиях
        # Transition /newNode_67
        go!: /newNode_35