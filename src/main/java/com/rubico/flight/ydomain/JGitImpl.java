package com.rubico.flight.ydomain;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.dfs.DfsRepositoryDescription;
import org.eclipse.jgit.internal.storage.dfs.InMemoryRepository;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathSuffixFilter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JGitImpl<T> {

    public <T> T get(T t) throws Exception {
        String BRANCH = "master";
        String uri = "https://github.com/rubico259/flight.git";


        Map<String, String> map = new HashMap<>();

        DfsRepositoryDescription repoDesc = new DfsRepositoryDescription();
        InMemoryRepository repo = new InMemoryRepository(repoDesc);
        org.eclipse.jgit.api.Git git = new Git(repo);
        git.fetch()
                .setRemote(uri)
                .setRefSpecs(new RefSpec("+refs/heads/*:refs/heads/*"))
                .call();
        repo.getObjectDatabase();

        ObjectId lastCommitId = repo.resolve("refs/heads/" + BRANCH);
        RevWalk revWalk = new RevWalk(repo);
        RevCommit commit = revWalk.parseCommit(lastCommitId);
        RevTree tree = commit.getTree();
        TreeWalk treeWalk = new TreeWalk(repo);
        treeWalk.addTree(tree);
        treeWalk.setRecursive(true);
        treeWalk.setFilter(PathSuffixFilter.create(".yaml"));

        ConvertY y = new ConvertY();
        while (treeWalk.next()) {

            String path = treeWalk.getPathString();

            ObjectId obj = treeWalk.getObjectId(0);
            ObjectLoader loader = repo.newObjectReader().open(obj);

            String s = new String(loader.getBytes());

            map.put(path, s);

        }

        return (T) y.convert(map, t);
    }
}
