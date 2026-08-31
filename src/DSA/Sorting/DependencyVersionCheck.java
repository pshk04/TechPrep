package Sorting;

import java.util.*;

public class DependencyVersionCheck {

    public static void main() {
        String[][] versionsList = {
                {
                        "2.0.0",
                        "1.3.0",
                        "1.2.5",
                        "1.4.0"
                },

        };

        String[][][] featuresList = {
                {
                        {"auth", "logging", "cache"},
                        {"auth", "logging"},
                        {"auth", "cache"},
                        {"auth", "logging", "cache", "metrics"}
                },
        };

        String[][] requiredFeaturesList = {
                {"auth","logging","cache"}
        };

        for(int i = 0 ; i < versionsList.length; i++){
            System.out.println("The version that supports all the required features is: "+
                    findEarliestVersion(versionsList[i],featuresList[i],requiredFeaturesList[i]));
        }
    }

    public static String findEarliestVersion(String[] versions, String[][] features, String[] requiredFeatures) {
        TreeMap<String, String[]> versionsFeaturesMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String v1, String v2) {
                String[] parts1 = v1.split("\\.");
                String[] parts2 = v2.split("\\.");

                int length = Math.min(parts1.length, parts2.length);
                for (int i = 0; i < length; i++) {
                    int p1 = Integer.parseInt(parts1[i]);
                    int p2 = Integer.parseInt(parts2[i]);

                    if (p1 != p2) {
                        return Integer.compare(p1, p2);
                    }
                }

                return Integer.compare(parts1.length, parts2.length);
            }
        });
        Arrays.sort(requiredFeatures);
        String matchingVersion = "";

        for(int i = 0 ; i < versions.length; i++){
            String[] featuresList = features[i];
            Arrays.sort(featuresList);
            versionsFeaturesMap.put(versions[i], featuresList);
        }

        for(Map.Entry<String, String[]> entry : versionsFeaturesMap.entrySet()){
            String[] currentFeature = entry.getValue();
            String version = entry.getKey();

            List<String> masterList = Arrays.asList(currentFeature);
            List<String> subsetList = Arrays.asList(requiredFeatures);

            if(masterList.containsAll(subsetList)){
                matchingVersion = version;
                break;
            }
        }
        return matchingVersion;
    }

}
