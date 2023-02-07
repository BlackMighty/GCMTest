package com.example.GCMTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@SpringBootApplication
@RestController
public class GcmTestApplication {

    private Map<Character, int[]> stats = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(GcmTestApplication.class, args);
    }

    @GetMapping("/api/analyze/{input_string}")
    public Map<Character, int[]> analyzeString(@PathVariable String inputString) {
        Map<Character, int[]> result = new HashMap<>();
        char[] chars = inputString.toCharArray();

        for (char c : chars) {
            if (!result.containsKey(c)) {
                result.put(c, new int[2]);
            }

            result.get(c)[0]++;

            int maxLength = 0;
            int currentLength = 0;

            for (char ch : chars) {
                if (ch == c) {
                    currentLength++;

                    if (currentLength > maxLength) {
                        maxLength = currentLength;
                    }
                } else {
                    currentLength = 0;
                }
            }

            result.get(c)[1] = maxLength;


            if (!stats.containsKey(c)) {
                stats.put(c, new int[2]);
            } else {
                stats.get(c)[0]++;
            }
        }
        return result;
    }


    @GetMapping("/api/statistics")
    public Map<Character, double[]> getStatistics() {
        Map<Character, double[]> statisticsResult = new HashMap<>();
        for (Map.Entry<Character, int[]> entry : stats.entrySet()) {
            double avgCountOfCharsInRequestWithThisChar = (double) entry.getValue()[0] / entry.getValue()[1];
            statisticsResult.put(entry.getKey(), new double[]{entry.getValue()[0], avgCountOfCharsInRequestWithThisChar});
        }
        return statisticsResult;
    }
}

