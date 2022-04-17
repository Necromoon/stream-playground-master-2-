package brickset;

import java.util.Map;
import java.util.stream.Collectors;

import repository.Repository;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * True értéket ad vissza ha létezik SpongeBob SquarePants a LegoSetben.
     */
    public boolean SpongeBob_SquarePants_Set_Theme (){
        return getAll().stream().anyMatch(legoSet -> legoSet.getTheme().equals("SpongeBob SquarePants"));
    }

    /**
     * Viiszaadja az összes Lego Set címke minimális hosszát.
     */
    public void The_Minimum_Length_Of_All_LegoSet_Tags(){
        getAll().stream()
                .filter(legoSet -> legoSet.getTags() != null)
                .flatMap(legoSet -> legoSet.getTags().stream())
                .mapToInt(String::length)
                .min()
                .ifPresent(System.out::println);
    }

    /**
     * Viiszaadja az összes darabot.
     */
    public int get_Total_Pieces() {
        var repository = new LegoSetRepository();
        return repository.getAll().stream()
                .map(LegoSet::getPieces)
                .reduce(0, Integer::sum);
    }

    /**
     * Visszaadja a darabokat a téma szerint
     */
    public Map<String, Integer> get_Pieces_By_Theme() {
        var repository = new LegoSetRepository();
        return repository.getAll().stream()
                .collect(Collectors.groupingBy(LegoSet::getTheme, Collectors.summingInt(LegoSet::getPieces)));
    }
    /**
     * Visszaadja az azonos témájú LegoSeteket és összeszámolja.
     */

    public Map<String, Long> Group_And_Count_LegoSets_By_Theme()
    {
        return getAll().stream().collect(Collectors.groupingBy(LegoSet::getTheme,Collectors.counting()));
    }


    public static void main(String[] args) {
        var repository = new LegoSetRepository();

        //1
        System.out.println("Létezik SpongeBob SquarePants Legoset: "+repository.SpongeBob_SquarePants_Set_Theme());
        //2
        System.out.print("Minimális Lego címke hossz: ");
        repository.The_Minimum_Length_Of_All_LegoSet_Tags();
        //3
        System.out.print("Összes darab: ");
        System.out.println(repository.get_Total_Pieces());
        //4
        System.out.print("Visszaadja a darabokat a téma szerint: ");
        System.out.println(repository.get_Pieces_By_Theme());
        //5
        System.out.print("Visszaadja az azonos témájú LegoSeteket és összeszámolja: ");
        System.out.print(repository.Group_And_Count_LegoSets_By_Theme());

    }

}