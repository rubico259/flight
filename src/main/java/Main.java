import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.dircache.DirCacheIterator;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.FileTreeIterator;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathSuffixFilter;
import org.eclipse.jgit.treewalk.filter.TreeFilter;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws GitAPIException, IOException {
        Git r = Git.cloneRepository()
                .setURI("https://github.com/rubico259/flight.git")
                .setDirectory(new File("dir/f"))
                .call();

        RevWalk rw = new RevWalk(r.getRepository());
        try (TreeWalk tw = new TreeWalk(r.getRepository())) {
            RevCommit commitToCheck = rw.parseCommit(r.getRepository().resolve("HEAD"));
            tw.addTree(commitToCheck.getTree());
            tw.addTree(new DirCacheIterator(r.getRepository().readDirCache()));
            tw.addTree(new FileTreeIterator(r.getRepository()));
            tw.setRecursive(true);
            TreeFilter treeFilter = PathSuffixFilter.create(".yaml");
            tw.setFilter(treeFilter);
            while (tw.next()) {


                String path = tw.getPathString();
                System.out.println(path);

                ObjectId obj = tw.getObjectId(0);
                ObjectLoader loader = r.getRepository().newObjectReader().open(obj);
                String s = new String(loader.getBytes());

            }
        }

        ObjectId lastCommitId = r.getRepository().resolve(Constants.HEAD);

        // a RevWalk allows to walk over commits based on some filtering that is defined
        try (RevWalk revWalk = new RevWalk(r.getRepository())) {
            RevCommit commit = revWalk.parseCommit(lastCommitId);
            // and using commit's tree find the path
            RevTree tree = commit.getTree();
            System.out.println("Having tree: " + tree);

            // now try to find a specific file
            try (TreeWalk treeWalk = new TreeWalk(r.getRepository())) {
                treeWalk.addTree(tree);
                treeWalk.setRecursive(true);

                ObjectId objectId = treeWalk.getObjectId(0);
                ObjectLoader loader = r.getRepository().open(objectId);

                // and then one can the loader to read the file
                loader.copyTo(System.out);
            }

            revWalk.dispose();
        }


    }
}