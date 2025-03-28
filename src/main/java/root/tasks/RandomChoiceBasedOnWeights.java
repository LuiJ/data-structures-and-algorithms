package root.tasks;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class RandomChoiceBasedOnWeights {

    // weight = probability --> item with the biggest weight has the biggest probability to be chosen

    @Mock
    private Random random;
    @InjectMocks
    private WeightedItemRandomSelector weightedItemRandomSelector;

    @Test
    void shouldChooseRandomItemBasedOnWeights() {
        // GIVEN
        Item item1 = new Item("item-1", 20);
        Item item2 = new Item("item-2", 50);
        Item item3 = new Item("item-3", 30);
        List<Item> items = List.of(item1, item2, item3);
        when(random.nextDouble()).thenReturn(0.3).thenReturn(0.1).thenReturn(0.8).thenReturn(0.15);

        // EXPECT
        assertThat(weightedItemRandomSelector.selectItem(items)).isEqualTo(item2);
        assertThat(weightedItemRandomSelector.selectItem(items)).isEqualTo(item1);
        assertThat(weightedItemRandomSelector.selectItem(items)).isEqualTo(item3);
        assertThat(weightedItemRandomSelector.selectItem(items)).isEqualTo(item1);
    }
}

@RequiredArgsConstructor
class WeightedItemRandomSelector {

    private final Random random;

    public <T extends WeightedItem> T selectItem(List<T> items) {
        List<Integer> weights = items.stream().map(WeightedItem::getWeight).collect(Collectors.toList());
        int totalWeight = items.stream().mapToInt(WeightedItem::getWeight).sum();
        int itemIndex = 0;
        for (double randomNumber = random.nextDouble() * totalWeight; itemIndex < weights.size() - 1; ++itemIndex) {
            randomNumber = randomNumber - weights.get(itemIndex);
            if (randomNumber <= 0) {
                break;
            }
        }
        return items.get(itemIndex);
    }
}

interface WeightedItem {
    Integer getWeight();
}

@Value
class Item implements WeightedItem {
    String label;
    Integer weight;
}
