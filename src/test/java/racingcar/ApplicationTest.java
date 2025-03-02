package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 전진_정지() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 이름에_대한_예외_처리() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 이름을_쉼표로_구분() {
        String input = "pobi,woni";
        String[] result = input.split(",");

        assertThat(result).contains("woni", "pobi");
        assertThat(result).containsExactly("pobi", "woni");
    }

    @Test
    void 이름_입력시_쉼표가_포함되지_않은_경우() {
        String input = "pobi";
        String[] result = input.split(",");

        assertThat(result).contains("pobi");
    }

    @Test
    void 이동횟수에_대한_예외처리() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,woni", "1번"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
