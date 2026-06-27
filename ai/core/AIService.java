package ai.core;

import ai.services.ClassificationService;
import ai.services.PriorityService;
import ai.services.ReplySuggestionService;
import ai.services.SearchService;
import ai.services.SummaryService;

public class AIService {

    private final SummaryService summaryService;
    private final SearchService searchService;
    private final ReplySuggestionService replyService;
    private final ClassificationService classificationService;
    private final PriorityService priorityService;

    public AIService() {

        summaryService = new SummaryService();
        searchService = new SearchService();
        replyService = new ReplySuggestionService();
        classificationService = new ClassificationService();
        priorityService = new PriorityService();

    }

    public String summarize(String conversation) {

        return summaryService.summarizeConversation(conversation);

    }

    public String search(String conversation, String question) {

        return searchService.searchConversation(conversation, question);

    }

    public String suggestReplies(String message) {

        return replyService.generateReplySuggestions(message);

    }

    public String classify(String message) {

        return classificationService.classifyMessage(message);

    }

    public String detectPriority(String message) {

        return priorityService.detectPriority(message);

    }

}