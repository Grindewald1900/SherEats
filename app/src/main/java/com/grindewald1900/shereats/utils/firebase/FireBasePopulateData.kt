package com.grindewald1900.shereats.utils.firebase

class FireBasePopulateData {
    companion object{
        fun populateUser(){
            RealtimeUtil.addUser("000000100", "Yee", "aaa", "M", "8190001111", "grindewald1504@gmail.com")
        }

        fun populateRestaurant(){
            RealtimeUtil.addRestaurant("0001", "McDonalds", "110 Rue Queen, Sherbrooke, QC J1M 1J6", "Fast Food",
                13.0, "+18195635828", 45.36750783294007, -71.85578947857024, "T")
            RealtimeUtil.addRestaurant("0002", "Tim Hortons", "89 Rue Queen, Sherbrooke, QC J1M 1J5", "Fast Food",
                9.0, "+18198237007",45.3654632370656 , -71.85717677305372, "F")
            RealtimeUtil.addRestaurant("0003", "Sushi King", "1234 Rue King Ouest, Sherbrooke, QC J1H 1S2", "Japanese Style",
                15.0, "+18197912438",45.39877034787492, -71.90770061538169,  "F")
            RealtimeUtil.addRestaurant("0004", "Chez Stanley", "1180 Rue King E, Sherbrooke, QC J1G 1E4", "Sports Bar",
                30.0, "+18195623350", 45.40812828776601,-71.8627545730523 , "F")
            RealtimeUtil.addRestaurant("0005", "Antidote FoodLab", "35 Rue Belvédère N suite 10, Sherbrooke, Quebec J1H 4A7", "Canadian Style",
                50.0  , "+18197919117",45.399877868749506 , -71.89720360845091, "")
            RealtimeUtil.addRestaurant("0006", "Chez Freddy Pizzeria", "835 Rue du Conseil, Sherbrooke, QC J1G 3L2", "Pizza",
                17.0, "+18198212248", 45.404532386620524, -71.8707963005016, "F")
            RealtimeUtil.addRestaurant("0007", "Sballo", "34 Rue Wellington N, Sherbrooke, QC J1H 5B7", "Italian Style",
                55.0, "+18197913559",45.40284504094125 , -71.89058027369387, "T")
            RealtimeUtil.addRestaurant("0008", "Restaurant An Phu", "1105 Rue King E, Sherbrooke, QC J1G 1E5", "Vietnamese Style",
                20.0   , "+18195691445", 45.409081942402146, -71.86470232260079, "F")
            RealtimeUtil.addRestaurant("0009", "Bâton Rouge Steakhouse & Bar", "2844 Rue King O, Sherbrooke, QC J1L 1C5", "Canadian Style",
                21.0 , "+18193469888", 45.397360883078335, -71.94195772279187, "F")
            RealtimeUtil.addRestaurant("0010", "Starbucks", "2201 Rue King O, Sherbrooke, QC J1J 2G2", "Coffee",
                22.0 , "+18195691445",45.39912831981553, -71.92763326114012, "F")
            RealtimeUtil.addRestaurant("0011", "Café Dépôt Sherbrooke", "Carrefour de l'Estrie, 3050 Boul de Portland, Sherbrooke, QC J1L 1K1", "Coffee",
                25.0, "+18195691445",45.401579876276195 , -71.95279212677053, "F")
            RealtimeUtil.addRestaurant("0012", "Brasserie Seigneurie", "14 Rue Léger, Sherbrooke, QC J1L 1W1", "Canadian Style",
                24.0, "+18195691445", 45.3866019281863, -71.96373425360962, "F")
            RealtimeUtil.addRestaurant("0013", "Caffuccino", "4257 Boul Bourque, Sherbrooke, QC J1N 1S4", "Canadian Style",
                30.0 , "+18195691445",45.384044325982615 , -71.96734517035223, "F")
            RealtimeUtil.addRestaurant("0014", "Créme Glacée En Folie", "2684 Rue Galt O, Sherbrooke, QC J1K 2X2", "Canadian Style",
                14.0 , "+18195691445", 45.38698382135774, -71.93945737742932, "F")
            RealtimeUtil.addRestaurant("0015", "Au Coin du Vietnam", "1530 Rue Galt O, Sherbrooke, QC J1H 2B4", "Vietnamese Style",
                28.0, "+18195691445",45.38706947871017 , -71.912783479273, "T")
            RealtimeUtil.addRestaurant("0016", "Subway", "1515 Rue Dunant, Sherbrooke, QC J1H 5N6", "Fast Food",
                16.0, "+18195691445",45.38364418448974 ,-71.89949276417899 , "T")
            RealtimeUtil.addRestaurant("0017", "Domino's Pizza", "1105 Rue  S, Sherbrooke, Quebec J1H 4C8", "Pizza",
                42.0, "+18195691445",45.38982554181572 , -71.89630534546536, "T")
        }

        fun populateDish(){
            RealtimeUtil.addDish("0001", "McDonalds", 1 , "Big Mac Bacon", "burger", 10.0, 1.0, 4.1)
            RealtimeUtil.addDish("0001", "McDonalds", 2 , "Double Western BBQ Quarter Pounder", "burger", 12.5, 1.0, 3.9)
            RealtimeUtil.addDish("0002", "Tim Hortons", 3 , "Brewed Coffee", "coffee", 1.76, 0.9, 2.7)
            RealtimeUtil.addDish("0002", "Tim Hortons", 4 , "Crispy Chicken Sandwich", "sandwish", 3.98, 1.0, 4.5)
            RealtimeUtil.addDish("0002", "Tim Hortons", 5 , "Beef Burger", "burger", 4.99, 1.0, 4.4)
            RealtimeUtil.addDish("0003", "Sushi King", 6, "Salmon Sushi 6 pcs", "sushi", 5.95, 0.8, 4.9 )
            RealtimeUtil.addDish("0003", "Sushi King", 7 , "Mix Salade", "salade", 7.75, 1.0, 5.0)
            RealtimeUtil.addDish("0004", "Chez Stanley", 8 , "Salade grecque", "salade", 15.0, 0.6, 2.8)
            RealtimeUtil.addDish("0004", "Chez Stanley", 9 , "Pepsi", "beverage", 12.5, 2.5, 3.6)
            RealtimeUtil.addDish("0004", "Chez Stanley", 10 , "Stanly Pizza", "pizza", 17.4, 1.0, 3.4)
            RealtimeUtil.addDish("0005", "Antidote FoodLab", 11 , "Salmon", "fish dishes", 28.0, 1.0, 3.2)
            RealtimeUtil.addDish("0005", "Antidote FoodLab", 12 , "Duck Breast", "duck dishes", 35.0, 0.7, 3.9)
            RealtimeUtil.addDish("0005", "Antidote FoodLab", 13 , "Toscana Pietranera 2018, Piaggia", "wine", 130.0, 1.0, 4.3)
            RealtimeUtil.addDish("0006", "Chez Freddy Pizzeria", 14 , "Italian Pizza", "pizza", 15.99, 1.0, 4.7)
            RealtimeUtil.addDish("0006", "Chez Freddy Pizzeria", 15 , "Club Sandwish", "sandwish", 13.99, 1.0, 4.0)
            RealtimeUtil.addDish("0007", "Sballo", 16 , "Carnaroli Risotto Seafood", "seafood dishes", 38.0, 1.0, 3.8)
            RealtimeUtil.addDish("0007", "Sballo", 17 , "Filettino di bue del Conte", "beef dishes", 42.0, 0.95, 3.5)
            RealtimeUtil.addDish("0008", "Restaurant An Phu", 18 , "Chicken Fried", "chicken dishes", 18.0, 1.0, 5.0)
            RealtimeUtil.addDish("0008", "Restaurant An Phu", 19 , "Fried Noodle", "noodle/pasta", 17.5, 1.0, 4.7)
            RealtimeUtil.addDish("0008", "Restaurant An Phu", 20 , "Sauteed Chicken in Peanut Sauce", "chicken dishes", 9.5, 1.0, 4.3)
        }

        fun populateBadge(){
            RealtimeUtil.addBadge("000000100", 1, 1,1,"[Lv1] First top up","2021-01-01",1)
            RealtimeUtil.addBadge("000000100", 2, 2,2,"[Lv1] Peace and love","2021-02-01",1)
            RealtimeUtil.addBadge("000000100", 3, 3,3,"[Lv2] All correct","2021-03-01",2)
            RealtimeUtil.addBadge("000000100", 4, 4,4,"[Lv1] Ecological volunteer","2021-04-01",1)
            RealtimeUtil.addBadge("000000100", 5, 5,5,"[Lv1] 100% Guarantee","2021-05-01",1)
            RealtimeUtil.addBadge("000000100", 6, 6,6,"[Lv3] Amazing","2021-06-01",3)
            RealtimeUtil.addBadge("000000100", 7, 7,7,"[Lv1] Sunshine and beach","2021-07-01",1)
            RealtimeUtil.addBadge("000000100", 8, 8,8,"[Lv1] Rock star","2021-08-01",1)
            RealtimeUtil.addBadge("000000100", 9, 9,9,"[Lv4] Lifetime learning","2021-09-01",4)
            RealtimeUtil.addBadge("000000100", 10, 1,10,"[Lv1] First top up","2021-09-03",1)
            RealtimeUtil.addBadge("000000100", 11, 3,11,"[Lv2] All correct","2021-09-06",2)
            RealtimeUtil.addBadge("000000100", 12, 6,12,"[Lv3] Amazing","2021-10-01",3)
            RealtimeUtil.addBadge("000000100", 13, 9,13,"[Lv4] Lifetime learning","2021-11-11",4)
            RealtimeUtil.addBadge("000000101", 14, 1,1,"[Lv1] First top up","2021-10-01",1)
            RealtimeUtil.addBadge("000000101", 15, 2,2,"[Lv1] Peace and love","2021-11-01",1)

        }
    }
}