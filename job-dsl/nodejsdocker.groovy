job('NodeJS Docker example') {
    scm {
        git('https://github.com/yemin7/nodejs-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('YeMin')
            node / gitConfigEmail('jenkins-dsl@tofu.me')
        }
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('yemin/nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('yemin')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
