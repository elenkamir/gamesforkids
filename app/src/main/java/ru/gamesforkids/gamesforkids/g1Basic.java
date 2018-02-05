package ru.gamesforkids.gamesforkids;

import java.util.ArrayList;

public class g1Basic {

    public void main() {

/*        красный - 1
                желтый - 2
                        синий - 3
                                белый - 4
                                        черный - 5*/
/*При запуске создаем массив из результирующих цветов.
                Потом рандомом будем доставать из него по одному цвету. */
        ArrayList<g1ResultColor>  g1ResultColors = new ArrayList<>();

        g1ResultColor purple = new g1ResultColor();
        purple.g1Title = String.valueOf(R.string.g1_purple);
        purple.g1ColorOne = 1;
        purple.g1ColorTwo = 3;
        purple.g1Color = "8b00ff";
        g1ResultColors.add(purple);

        g1ResultColor orange = new g1ResultColor();
        orange.g1Title = String.valueOf(R.string.g1_orange);
        orange.g1ColorOne = 1;
        orange.g1ColorTwo = 2;
        orange.g1Color = "ffa500";
        g1ResultColors.add(orange);




    }
}
